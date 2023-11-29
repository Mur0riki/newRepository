package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecursiveBacktrackingGenerator implements Generator {
    private final Random random;

    public RecursiveBacktrackingGenerator() {
        this.random = new Random();
    }

    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        // Инициализируем все клетки лабиринта как стены
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }

        // Посещенные клетки
        boolean[][] visited = new boolean[height][width];

        // Начинаем генерацию с случайной стартовой клетки
        int startX = random.nextInt(height);
        int startY = random.nextInt(width);
        generatePath(grid, visited, startX, startY);

        return new Maze(height, width, grid);
    }

    private void generatePath(Cell[][] grid, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);

        // Соседние клетки
        List<int[]> neighbors = getUnvisitedNeighbors(grid, visited, row, col);

        // Перемешиваем порядок соседних клеток
        shuffleList(neighbors);

        // Рекурсивно генерируем путь в соседние клетки
        for (int[] neighbor : neighbors) {
            int neighborRow = neighbor[0];
            int neighborCol = neighbor[1];

            if (!visited[neighborRow][neighborCol]) {
                generatePath(grid, visited, neighborRow, neighborCol);
            }
        }
    }

    private List<int[]> getUnvisitedNeighbors(Cell[][] grid, boolean[][] visited, int row, int col) {
        List<int[]> neighbors = new ArrayList<>();

        // Верхняя клетка
        if (row > 1 && !visited[row - 2][col]) {
            neighbors.add(new int[]{row - 2, col});
        }

        // Правая клетка
        if (col < grid[0].length - 2 && !visited[row][col + 2]) {
            neighbors.add(new int[]{row, col + 2});
        }

        // Нижняя клетка
        if (row < grid.length - 2 && !visited[row + 2][col]) {
            neighbors.add(new int[]{row + 2, col});
        }

        // Левая клетка
        if (col > 1 && !visited[row][col - 2]) {
            neighbors.add(new int[]{row, col - 2});
        }

        return neighbors;
    }

    private void shuffleList(List<int[]> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
