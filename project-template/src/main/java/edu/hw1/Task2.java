package edu.hw1;

public class Task2 {
    public int countDigit(int digit) {
        int count = 0;
        digit = Math.absExact(digit);
        if (digit == 0) {
            return 1;
        } else {
            while (digit > 0) {
                digit /= 10;
                count++;
            }
            return count;
        }
    }
}
