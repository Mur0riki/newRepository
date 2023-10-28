package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Task5 {
    public static void main(String[] args) {
        String[] array = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        ArrayList<Contact> contacts = new ArrayList<>(array.length);
        Task5 task5 = new Task5();
        contacts = task5.parseContact(array,"DESC");
        for(Contact contact:contacts){
            System.out.println(contact.getName() + " " + contact.getSurname());
        }
    }
    public ArrayList<Contact> parseContact(String[] array, String type) {
        ArrayList<Contact> contacts = new ArrayList<>(array.length);
        if (type.equals("ASC")) {
            Arrays.sort(array, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] splittedLine = o1.split(" ");
                    String[] splittedLine2 = o2.split(" ");
                    if ((splittedLine2.length > 2) || (splittedLine2.length == 0) || (splittedLine.length > 2) ||
                        (splittedLine.length == 0)) {
                        throw new RuntimeException("Wrong input");
                    }
                    if (splittedLine2.length == 2 && splittedLine.length == 2) {
                        return splittedLine[1].compareTo(splittedLine2[1]);
                    } else {
                        throw new RuntimeException("Wrong input");
                    }
                }
            });
        }
        if (type.equals("DESC")) {
            Arrays.sort(array, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] splittedLine = o1.split(" ");
                    String[] splittedLine2 = o2.split(" ");
                    if ((splittedLine2.length > 2) || (splittedLine2.length == 0) || (splittedLine.length > 2) ||
                        (splittedLine.length == 0)) {
                        throw new RuntimeException("Wrong input");
                    }
                    if (splittedLine2.length == 2 && splittedLine.length == 2) {
                        return splittedLine2[1].compareTo(splittedLine[1]);
                    } else {
                        throw new RuntimeException("Wrong input");
                    }
                }
            });
        }
        for(String fullname: array){
            String[] splittedLine = fullname.split(" ");
            if(splittedLine.length == 2){
                contacts.add(new Contact(splittedLine[0], splittedLine[1]));
            } else {
                contacts.add(new Contact(splittedLine[0]));
            }
        }
        return contacts;
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
