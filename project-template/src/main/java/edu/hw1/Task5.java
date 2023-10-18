package edu.hw1;

public class Task5 {
    public boolean isPalindrome(int digit) {
        boolean flag = true;
        String numberString = Integer.toString(digit);
        char[] charArray = numberString.toCharArray();

        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = Character.getNumericValue(charArray[i]);
        }
        if (intArray.length < 2) {
            return false;
        }
        for (int i = 0; i <= intArray.length / 2; i++) {
            if (flag) {
                if (intArray[i] != intArray[intArray.length - i - 1]) {
                    flag = false;
                }
            }
        }
        if (!flag) {
            int newDigit = 0;
            for (int i = 0; i < intArray.length; i += 2) {
                if (intArray.length - i == 1) {
                    break;
                }
                newDigit *= 10;
                newDigit += intArray[i] + intArray[i + 1];
            }
            if (intArray.length % 2 != 0) {
                newDigit *= 10;
                newDigit = intArray[intArray.length - 1];
            }
            flag = isPalindrome(newDigit);
        }
        return flag;
    }
}
