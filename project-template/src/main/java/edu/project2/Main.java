package edu.project2;

public class Main {
    public static void main(String[] args) {
        Generator generator = new RecursiveBacktrackingGenerator();
        Maze maze = generator.generate(10, 10);
        MazeRenderer mazeRenderer = new MazeRenderer();
        System.out.println(mazeRenderer.render(maze));
    }
}
