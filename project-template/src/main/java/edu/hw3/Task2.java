package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public List<String> bracketClustering(String string) {
        StringBuilder brackets = new StringBuilder(string);
        List<String> output = new ArrayList<>();
        int start = 0;
        int finish = 0;
        int openbracket = 0;
        int closedbracket = 0;
        boolean error = false;
        for (char ch : string.toCharArray()) {
            if (ch == '(') {
                openbracket++;
            } else if (ch == ')') {
                closedbracket++;
            } else {
                error = true;
                break;
            }
            finish++;
            if (openbracket == closedbracket) {
                output.add(string.substring(start, finish));
                start = finish;
            }
            if (finish == string.length() && (openbracket != closedbracket)) {
                throw new RuntimeException("Wrong input.Too many opening or closing brackets");
            }
        }
        if (error) {
            throw new RuntimeException("Wrong input.Other symbols.");
        }
        return output;
    }
}
