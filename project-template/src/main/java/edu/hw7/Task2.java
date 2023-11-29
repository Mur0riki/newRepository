package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {
    class FactorialCalculator {

        public static long factorial(long number) {
            return LongStream.rangeClosed(1, number)
                .parallel()
                .reduce(1, (fact, i) -> fact * i);
        }
    }

    public static void main(String[] args) {
        long number = 10;
        long factorial = FactorialCalculator.factorial(number);
        System.out.println("Факториал числа " + number + " равен " + factorial);
    }
}
