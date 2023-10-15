package edu.hw1;

public class Task7 {

    public int rotateLeft(int digit, int shift){
        String binaryDigit = Integer.toBinaryString(digit);
        if ((binaryDigit.length() == shift) || digit == 1 || shift == 0) return  digit;
        int answer = (digit << shift) | (digit >> (binaryDigit.length() - shift));
        return (int) (answer%(Math.pow(2,binaryDigit.length())));
    }

    public int rotateRight(int digit, int shift){
        String binaryDigit = Integer.toBinaryString(digit);
        if ((binaryDigit.length() == shift) || digit == 1 || shift == 0) return  digit;
        int answer = (digit >> shift) | (digit << (binaryDigit.length() - shift));
        return (int) (answer%(Math.pow(2,binaryDigit.length())));
    }
}
