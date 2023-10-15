package edu.hw1;

public class Task3 {
    public boolean isNestable(int[] array1, int[]array2){
        int min1 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int count:array1){
            if (count < min1) min1 = count;
            if (count > max1) max1 = count;
        }
        for(int count:array2){
            if (count < min2) min2 = count;
            if (count > max2) max2 = count;
        }
        if ((min1 > min2)&&(max1 < max2))
            return true;
        return false;
    }
}
