package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {
    int countkk = 1;
    public int countK(int digit){
        String numberString = Integer.toString(digit);
        char[] charArray = numberString.toCharArray();

        int[] firstDigit = new int[charArray.length];
        int[] secondDigit = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            firstDigit[i] = Character.getNumericValue(charArray[i]);
            secondDigit[i] = Character.getNumericValue(charArray[i]);
        }
        Arrays.sort(firstDigit);
        Integer[] arr = Arrays.stream(secondDigit).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        int greaterNumber = 0;
        for(int number: arr){
            greaterNumber = greaterNumber*10 + number;
        }
        int smallerNumber = 0;
        for(int number: firstDigit){
            smallerNumber = smallerNumber*10+number;
        }
        if ((greaterNumber - smallerNumber) != 6174) {
            countkk++;
            countK(greaterNumber - smallerNumber);
        }
        return countkk;
    }
}
