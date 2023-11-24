package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterExample {
    private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        CounterExample counterExample = new CounterExample();
        counterExample.runThreads();
    }

    private void runThreads() {
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new IncrementTask());
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter.get());
    }

    private class IncrementTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
