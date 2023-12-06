package edu.hw8.task2;

public class Task2 {
    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = FixedThreadPool.create(30);
        threadPool.start();

        int n = 10;
        for (int i = 0; i < n; i++) {
            final int fibIndex = i;
            threadPool.execute(() -> {
                long fibNumber = calculateFibonacci(fibIndex);
                System.out.println("Fibonacci number at index " + fibIndex + ": " + fibNumber);
            });
        }
        threadPool.close();
    }

    private static long calculateFibonacci(int n) {
        if (n <= 1)
            return n;

        long fib0 = 0;
        long fib1 = 1;
        long fibNumber = 0;

        for (int i = 2; i <= n; i++) {
            fibNumber = fib0 + fib1;
            fib0 = fib1;
            fib1 = fibNumber;
        }

        return fibNumber;
    }
}
