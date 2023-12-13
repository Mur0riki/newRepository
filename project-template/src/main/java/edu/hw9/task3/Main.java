package edu.hw9.task3;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'D');
        graph.addEdge('C', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('E', 'F');

        MultiThreadedDFS dfs = new MultiThreadedDFS(graph);

        try {
            String result = dfs.threadedDFS('A');
            System.out.println("DFS traversal: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
