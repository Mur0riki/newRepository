package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static boolean isSubsequence(String S, String T) {
        String regex = ".*" + String.join(".*", S.split("")) + ".*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(T);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String S = "abc";
        String T = "achfdbaabgabcaabg";
        boolean isSubsequence = isSubsequence(S, T);
        System.out.println("Is " + S + " a subsequence of " + T + "? " + isSubsequence);
    }
}
