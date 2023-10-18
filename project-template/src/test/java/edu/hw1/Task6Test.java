package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task6Test {
    private Task6 task;

    @BeforeEach
    void setUp() {

        task = new Task6();
    }

    @Test
    void test1() {
        int answer = task.countK(3524);
        assertEquals(3, answer);
    }

    @Test
    void test2() {
        int answer = task.countK(6621);
        assertEquals(5, answer);
    }

    @Test
    void test3() {
        int answer = task.countK(6554);
        assertEquals(4, answer);
    }

    @Test
    void test4() {
        int answer = task.countK(1234);
        assertEquals(3, answer);
    }

    @Test
    void test5() {
        int answer = task.countK(1111);
        assertEquals(-1, answer);
    }
    @Test
    void test6() {
        int answer = task.countK(1112);
        assertEquals(5, answer);
    }
    @Test
    void test7() {
        int answer = task.countK(6174);
        assertEquals(0, answer);
    }
}
