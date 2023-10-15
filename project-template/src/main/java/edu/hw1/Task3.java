package edu.hw1;

import java.util.Arrays;

public class Task3 {
    public boolean isNestable(int[] array1, int[] array2) {
        int array1MaxValue = Arrays.stream(array1).max().getAsInt();
        int array2MaxValue = Arrays.stream(array2).max().getAsInt();
        int array1MinValue = Arrays.stream(array1).min().getAsInt();
        int array2MinValue = Arrays.stream(array2).min().getAsInt();
        return (array1MinValue > array2MinValue) && (array1MaxValue < array2MaxValue);
    }
}
