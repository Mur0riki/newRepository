package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task8Test {
    private Task8 task;

    @BeforeEach
    void setUp() {

        task =new Task8();
    }
    @Test
    void test1() {
        int[][] array;
        array = new int[][] {{0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0},{0, 1, 0, 0, 0, 1, 0, 0},{0, 0, 0, 0, 1, 0, 1, 0},{0, 1, 0, 0, 0, 1, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0},{0, 1, 0, 0, 0, 0, 0, 1},{0, 0, 0, 0, 1, 0, 0, 0}};
        boolean flag = task.knightBoardCapture(array);
        Assertions.assertTrue(flag);
    }
    @Test
    void test2() {
        int[][] array;
        array = new int[][]{{1, 0, 1, 0, 1, 0, 1, 0},{0, 1, 0, 1, 0, 1, 0, 1},{0, 0, 0, 0, 1, 0, 1, 0},{0, 0, 1, 0, 0, 1, 0, 1},{1, 0, 0, 0, 1, 0, 1, 0},{0, 0, 0, 0, 0, 1, 0, 1},{1, 0, 0, 0, 1, 0, 1, 0},{0, 0, 0, 1, 0, 1, 0, 1}};
        boolean flag = task.knightBoardCapture(array);
        Assertions.assertFalse(flag);
    }
}
