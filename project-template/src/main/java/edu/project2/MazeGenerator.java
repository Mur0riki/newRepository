package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private static final char WALL = '#';
    private static final char PATH = ' ';
    private static final int SIZE = 10;

    private char[][] maze;

    private Random random;

    public MazeGenerator() {
        maze = new char[SIZE][SIZE];
        random = new Random();
        generate();
    }

    public void generate() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                maze[i][j] = WALL;
            }
        }
        generatePath(0, 0);
    }

    public enum Direction {
        UP(0, -2),
        DOWN(0, 2),
        LEFT(-2, 0),
        RIGHT(2, 0);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private void generatePath(int x, int y) {
        maze[y][x] = PATH;

        List directions = new ArrayList<>(List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        Collections.shuffle(directions);

        for (int i = 0; i < 4; i++) {
            Direction direction = (Direction) directions.get(i);
            int newX = x + direction.getX();
            int newY = y + direction.getY();

            if (isValid(newX, newY) && maze[newY][newX] == WALL) {
                maze[(y + newY) / 2][(x + newX) / 2] = PATH;
                generatePath(newX, newY);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public void render() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.render();
    }
}
