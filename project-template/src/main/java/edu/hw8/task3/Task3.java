package edu.hw8.task3;

import java.util.HashMap;
import java.util.Scanner;

class Task3 {
    public static void main(String[] args) {
        HashMap<String, String> database = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int z = 0;
        while (z < 6) {
            System.out.println("Input hash and name");
            String hash = in.nextLine();
            String name = in.nextLine();
            database.put(hash, name);
            z++;
        }
        HashMap<String, String> decodeDatabase = new HashMap<>();
        String password = "";
        String newPassword = "";
        while (database.size() != decodeDatabase.size()) {
            MD5Hash md5Hash = new MD5Hash();
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            newPassword = passwordGenerator.generateNextPassword(password);
            password = newPassword;
            String hashPassword = md5Hash.getMD5Hash(password);
            if (database.containsKey(hashPassword)) {
                decodeDatabase.put(database.get(hashPassword), password);
                System.out.println("TRUE!");
            }
        }
        System.out.println(decodeDatabase.toString());
    }

}
