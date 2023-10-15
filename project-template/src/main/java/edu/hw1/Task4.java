package edu.hw1;

public class Task4 {
    public String fixString(String string){
        char[] array = string.toCharArray();
        int index = 0;
        while (index < array.length){
            if(array.length - index == 1) break;
            char symbol = array[index];
            array[index] = array[index+1];
            array[index+1] = symbol;
            index+=2;
        }
        return String.valueOf(array);
    }
}
