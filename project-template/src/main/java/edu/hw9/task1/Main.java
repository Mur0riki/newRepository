package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StatsCollector {
    private Map<String, List<Double>> data;
    private ExecutorService executor;

    public StatsCollector() {
        this.data = new ConcurrentHashMap<>();
        this.executor = Executors.newCachedThreadPool();
    }

    public synchronized void push(String metricName, double[] values) {
        data.putIfAbsent(metricName, new ArrayList<>());
        for (double value : values) {
            data.get(metricName).add(value);
        }
    }

    public synchronized List<Stats> stats() {
        List<Stats> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            String metricName = entry.getKey();
            List<Double> values = entry.getValue();

            double sum = 0;
            double average = 0;
            double maximum = Double.MIN_VALUE;
            double minimum = Double.MAX_VALUE;
            for (double value : values) {
                sum += value;
                if (value > maximum) {
                    maximum = value;
                }
                if (value < minimum) {
                    minimum = value;
                }
            }
            if (!values.isEmpty()) {
                average = sum / values.size();
            }

            // Create stats object and add it to the result
            Stats stats = new Stats(metricName, sum, average, maximum, minimum);
            result.add(stats);
        }
        return result;
    }

    public void shutdown() {
        executor.shutdown();
    }
}

class Stats {
    private String metricName;
    private double sum;
    private double average;
    private double maximum;
    private double minimum;

    public Stats(String metricName, double sum, double average, double maximum, double minimum) {
        this.metricName = metricName;
        this.sum = sum;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public double getSum(){
        return sum;
    }
    public double getAverage(){
        return average;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public String getMetricName() {
        return metricName;
    }
}

public class Main {
    public static void main(String[] args) {
        StatsCollector collector = new StatsCollector();

        double[] values1 = {0.1, 0.05, 1.4, 5.1, 0.3};
        double[] values2 = {2.5, 3.7, 0.8, 1.2};

        collector.push("metric_name_1", values1);
        collector.push("metric_name_2", values2);

        List<Stats> stats = collector.stats();
        for (Stats s : stats) {
            System.out.println(s.getMetricName());
            System.out.println("Sum: " + s.getSum());
            System.out.println("Average: " + s.getAverage());
            System.out.println("Maximum: " + s.getMaximum());
            System.out.println("Minimum: " + s.getMinimum());
            System.out.println();
        }

        collector.shutdown();
    }
}
