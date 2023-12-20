package edu.project2;

import java.util.List;

public class MazeRenderer implements Renderer {
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);

                if (cell.type() == Cell.Type.WALL) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public String render(Maze maze, List<Coordinate> path) {
        char[][] grid = new char[maze.getHeight()][maze.getWidth()];

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                grid[i][j] = maze.getCell(i, j).type() == Cell.Type.WALL ? '#' : ' ';
            }
        }

        for (Coordinate coordinate : path) {
            grid[coordinate.row()][coordinate.col()] = '*';
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                sb.append(grid[i][j]);
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
