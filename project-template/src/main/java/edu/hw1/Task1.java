package edu.hw1;

public class Task1 {
    public int videoLengthToSeconds(String length) {
        String[] splittedLenght = length.split(":");
        if (splittedLenght.length != 2) {
            return -1;
        }
        int videoLenght;
        int sec = Integer.parseInt(splittedLenght[1]);
        int min = Integer.parseInt(splittedLenght[0]);
        if (sec < 0 || sec > 60) {
            return -1;
        }
        return min * 60 + sec;
    }
}
