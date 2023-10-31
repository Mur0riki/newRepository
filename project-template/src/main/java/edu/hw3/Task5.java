package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Task5 {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Zxc","Ghoul"));
        contacts.add(new Contact("NotZxc","Ghoul"));
        Task5 task5 = new Task5();
        parseContact(contacts,"DESC");
        for(Contact contact:contacts){
            System.out.println(contact.getName() + " " + contact.getSurname());
        }
    }
    public static void parseContact(ArrayList<Contact> array, String type) {
        final Comparator<Contact> ASC = Comparator
            .comparing(Contact::getSurname)
            .thenComparing(Contact::getName);
        final Comparator<Contact> DESC = ASC.reversed();
        if (type.equals("ASC")) {
            Collections.sort(array, ASC);
        }
        if (type.equals("DESC")) {
            Collections.sort(array, DESC);
        }
    }
}

class Contact {
    String name;
    String surname;

    Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    Contact(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

}
