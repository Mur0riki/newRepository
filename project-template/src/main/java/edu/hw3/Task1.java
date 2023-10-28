package edu.hw3;

public class Task1 {
    public String atbachCipher(String input) {
        StringBuilder encrypted = new StringBuilder();
        char[] array = input.toCharArray();
        for (char ch : array) {
            if (Character.isLetter(ch)) {
                char encryptedChar;
                if ((int) ch >= 65 && (int) ch <= 90) {
                    int distanceFromA = ch - 'A';
                    encryptedChar = (char) ('Z' - distanceFromA);
                } else {
                    int distanceFromA = ch - 'a';
                    encryptedChar = (char) ('z' - distanceFromA);
                }
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }
}
