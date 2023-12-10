package edu.hw9.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Character, List<Character>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(char fromNode, char toNode) {
        adjacencyList.computeIfAbsent(fromNode, k -> new ArrayList<>()).add(toNode);
    }

    public List<Character> getNeighbours(char node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }
}
