package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Person(int id, String name, String address, String phoneNumber) {
}

interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    List<Person> findByPhone(String phone);
}

class CachingPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> cacheById = new HashMap<>();
    private final Map<String, List<Person>> cacheByName = new HashMap<>();
    private final Map<String, List<Person>> cacheByAddress = new HashMap<>();
    private final Map<String, List<Person>> cacheByPhone = new HashMap<>();

    private final Object lock = new Object();

    @Override
    public void add(Person person) {
        synchronized (lock) {
            cacheById.put(person.id(), person);

            updateCache(cacheByName, person.name(), person);
            updateCache(cacheByAddress, person.address(), person);
            updateCache(cacheByPhone, person.phoneNumber(), person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (lock) {
            Person person = cacheById.remove(id);

            if (person != null) {
                removeFromCache(cacheByName, person.name(), person);
                removeFromCache(cacheByAddress, person.address(), person);
                removeFromCache(cacheByPhone, person.phoneNumber(), person);
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (lock) {
            return cacheByName.getOrDefault(name, new ArrayList<>());
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (lock) {
            return cacheByAddress.getOrDefault(address, new ArrayList<>());
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (lock) {
            return cacheByPhone.getOrDefault(phone, new ArrayList<>());
        }
    }

    private void updateCache(Map<String, List<Person>> cache, String attributeValue, Person person) {
        List<Person> list = cache.computeIfAbsent(attributeValue, k -> new ArrayList<>());
        list.add(person);
    }

    private void removeFromCache(Map<String, List<Person>> cache, String attributeValue, Person person) {
        List<Person> list = cache.get(attributeValue);
        if (list != null) {
            list.remove(person);
            if (list.isEmpty()) {
                cache.remove(attributeValue);
            }
        }
    }
}
