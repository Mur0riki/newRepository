package edu.hw9.task3;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MultiThreadedDFS {
    private Set<Character> visited;
    private Stack<Character> result;
    private Graph graph;

    public MultiThreadedDFS(Graph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.result = new Stack<>();
    }

    public String threadedDFS(char startNode) throws InterruptedException {
        Thread[] threads = new Thread[graph.getNeighbours(startNode).size()];

        int i = 0;
        for (char neighbour : graph.getNeighbours(startNode)) {
            threads[i] = new Thread(() -> dfs(neighbour));
            threads[i].start();
            i++;
        }

        for (Thread thread : threads) {
            thread.join();
        }

        StringBuilder sb = new StringBuilder();
        while (!result.empty()) {
            sb.append(result.pop()).append(" ");
        }

        return sb.toString().trim();
    }

    private void dfs(char node) {

        synchronized (visited) {
            if (visited.contains(node)) {
                return;
            }
        }

        synchronized (result) {
            result.push(node);
        }

        synchronized (visited) {
            visited.add(node);
        }

        for (char neighbour : graph.getNeighbours(node)) {
            dfs(neighbour);
        }
    }
}
