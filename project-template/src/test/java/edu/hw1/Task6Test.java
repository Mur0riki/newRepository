package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task6Test {
    private Task6 task;

    @BeforeEach
    void setUp() {

        task =new Task6();
    }

    @Test
    void test1() {
        int answer = task.countK(3524);
        Assertions.assertEquals(3,answer);
    }
    @Test
    void test2() {
        int answer = task.countK(6621);
        Assertions.assertEquals(5,answer);
    }
    @Test
    void test3() {
        int answer = task.countK(6554);
        Assertions.assertEquals(4,answer);
    }
    @Test
    void test4() {
        int answer = task.countK(1234);
        Assertions.assertEquals(3,answer);
    }
}
