package edu.hw1;
public class Task1 {
    public int videoLengthToSeconds( String length){
        String[] splittedLenght = length.split(":");
        int videoLenght;
        int sec = Integer.parseInt(splittedLenght[1]);
        int min = Integer.parseInt(splittedLenght[0]);
        videoLenght = (sec < 0 || sec > 60)?-1:(min*60+sec);
        return videoLenght;
    }
}
