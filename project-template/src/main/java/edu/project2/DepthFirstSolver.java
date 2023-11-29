package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSolver implements Solver {
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];

        // Находим путь с помощью Depth-First Search
        dfs(maze, start, end, visited, path);

        return path;
    }

    private boolean dfs(Maze maze, Coordinate current, Coordinate end, boolean[][] visited, List<Coordinate> path) {
        int row = current.row();
        int col = current.col();

        // Проверяем, что текущая позиция находится в пределах лабиринта
        if (row < 0 || row >= maze.getHeight() || col < 0 || col >= maze.getWidth()) {
            return false;
        }

        // Проверяем, что текущая позиция не является стеной и не была посещена ранее
        if (maze.getCell(row, col).type() == Cell.Type.WALL || visited[row][col]) {
            return false;
        }

        // Добавляем текущую позицию в путь
        path.add(new Coordinate(row, col));

        // Проверяем, является ли текущая позиция конечной
        if (current.equals(end)) {
            return true;
        }

        // Помечаем текущую позицию как посещенную
        visited[row][col] = true;

        // Проверяем соседние клетки
        if (dfs(maze, new Coordinate(row - 1, col), end, visited, path) ||
            dfs(maze, new Coordinate(row, col + 1), end, visited, path) ||
            dfs(maze, new Coordinate(row + 1, col), end, visited, path) ||
            dfs(maze, new Coordinate(row, col - 1), end, visited, path)) {
            return true;
        }

        // Если путь не найден, удаляем текущую позицию из пути
        path.remove(path.size() - 1);

        return false;
    }
}
