package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {
    public HashMap<Object, Integer> freqDict(List dict) {
        HashMap<Object, Integer> frequensy = new HashMap<>();
        for (Object smth : dict) {
            if (frequensy.containsKey(smth)) {
                Integer temp = frequensy.get(smth);
                frequensy.put(smth, ++temp);
            } else {
                frequensy.put(smth, 1);
            }
        }
        return frequensy;
    }
}
