package edu.hw7.Task35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

record Person(int id, String name, String address, String phoneNumber) {}

interface PersonDatabase {
    void add(Person person);
    void delete(int id);

    List<Person> findByName(String name);
    List<Person> findByAddress(String address);
    List<Person> findByPhone(String phone);
}

public class CachingPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> cacheById = new HashMap<>();
    private final Map<String, List<Person>> cacheByName = new HashMap<>();
    private final Map<String, List<Person>> cacheByAddress = new HashMap<>();
    private final Map<String, List<Person>> cacheByPhone = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            cacheById.put(person.id(), person);

            updateCache(cacheByName, person.name(), person);
            updateCache(cacheByAddress, person.address(), person);
            updateCache(cacheByPhone, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = cacheById.remove(id);

            if (person != null) {
                removeFromCache(cacheByName, person.name(), person);
                removeFromCache(cacheByAddress, person.address(), person);
                removeFromCache(cacheByPhone, person.phoneNumber(), person);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return cacheByName.getOrDefault(name, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return cacheByAddress.getOrDefault(address, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phoneNumber) {
        lock.readLock().lock();
        try {
            return cacheByPhone.getOrDefault(phoneNumber, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    private void updateCache(Map<String, List<Person>> cache, String key, Person person) {
        List<Person> persons = cache.computeIfAbsent(key, k -> new ArrayList<>());
        persons.add(person);
    }

    private void removeFromCache(Map<String, List<Person>> cache, String key, Person person) {
        List<Person> persons = cache.get(key);
        if (persons != null) {
            persons.remove(person);
            if (persons.isEmpty()) {
                cache.remove(key);
            }
        }
    }
}
