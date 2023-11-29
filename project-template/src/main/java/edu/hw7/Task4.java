package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Task4 {
    static final int NUM_POINTS = 1000000;
    static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int pointsPerThread = NUM_POINTS / NUM_THREADS;

        AtomicInteger totalPointsInCircle = new AtomicInteger();
        for (int i = 0; i < NUM_THREADS; i++) {
            int finalI = i;
            executor.submit(() -> {
                int pointsInCircle = 0;
                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int j = 0; j < pointsPerThread; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1) {
                        pointsInCircle++;
                    }
                }
                synchronized (Task4.class) {
                    totalPointsInCircle.addAndGet(pointsInCircle);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        double pi = 4.0 * totalPointsInCircle.get() / NUM_POINTS;
        System.out.println("Число Пи: " + pi);
    }
}
