package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static boolean validatePassword(String password) {
        String regex = "[~!@#$%^&*|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static void main(String[] args) {
        String password = "password1";
        boolean isValid = validatePassword(password);
        System.out.println("Is password valid? " + isValid);
    }
}
