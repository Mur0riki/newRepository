package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task7Test {
    private Task7 task;

    @BeforeEach
    void setUp() {

        task = new Task7();
    }

    @Test
    void test1() {
        int answer = task.rotateLeft(16, 1);
        assertEquals(1, answer);
    }

    @Test
    void test2() {
        int answer = task.rotateRight(5, 1);
        assertEquals(6, answer);
    }

    @Test
    void test3() {
        int answer = task.rotateRight(22, 3);
        assertEquals(26, answer);
    }

    @Test
    void test4() {
        int answer = task.rotateLeft(22, 3);
        assertEquals(21, answer);
    }

    @Test
    void test5() {
        int answer = task.rotateLeft(0, 1);
        assertEquals(0, answer);
    }

    @Test
    void test6() {
        int answer = task.rotateRight(0, 1);
        assertEquals(0, answer);
    }

    @Test
    void test7() {
        int answer = task.rotateRight(1, 5);
        assertEquals(1, answer);
    }

    @Test
    void test8() {
        int answer = task.rotateLeft(1, 5);
        assertEquals(1, answer);
    }

    @Test
    void test9() {
        int answer = task.rotateRight(16, 5);
        assertEquals(16, answer);
    }

    @Test
    void test10() {
        int answer = task.rotateLeft(16, 5);
        assertEquals(16, answer);
    }
}
