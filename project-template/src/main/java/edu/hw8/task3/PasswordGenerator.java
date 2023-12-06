package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;

class PasswordGenerator {
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MAX_LENGTH = 6;

    public String generateNextPassword(String lastPassword) {
        List<Character> passwordChars = new ArrayList<>();
        int lastCharIndex = 0;
        if (lastPassword.isEmpty()) {
            passwordChars.add(CHARACTERS.charAt(0));
            return passwordCharsToString(passwordChars);
        } else {
            for (char c : lastPassword.toCharArray()) {
                passwordChars.add(c);
            }
            lastCharIndex = CHARACTERS.indexOf(lastPassword.charAt(lastPassword.length() - 1));
        }
        if (lastCharIndex == 61) {
            int lastMaxIndex = passwordChars.size() - 1;
            while (lastMaxIndex != -1 && CHARACTERS.indexOf(passwordChars.get(lastMaxIndex)) == 61) {
                lastMaxIndex--;
                if (lastMaxIndex == -1) {
                    break;
                }
            }
            if (lastMaxIndex == -1) {
                passwordChars.add(CHARACTERS.charAt(0));
                for (int i = 0; i <= passwordChars.size() - 2; i++) {
                    passwordChars.set(i, CHARACTERS.charAt(0));
                }
            } else {
                passwordChars.set(
                    lastMaxIndex,
                    CHARACTERS.charAt(CHARACTERS.indexOf(passwordChars.get(lastMaxIndex)) + 1)
                );
                for (int i = lastMaxIndex; i < passwordChars.size() - 1; i++) {
                    passwordChars.set(i + 1, CHARACTERS.charAt(0));
                }
            }

        } else {
            passwordChars.set(
                passwordChars.size() - 1,
                CHARACTERS.charAt(CHARACTERS.indexOf(passwordChars.get(passwordChars.size() - 1)) + 1)
            );
        }
        String newPassword = passwordCharsToString(passwordChars);

        if (newPassword.length() > MAX_LENGTH) {
            return null;
        }

        return newPassword;
    }

    public static String passwordCharsToString(List<Character> passwordChars) {
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }
        return password.toString();
    }

}
