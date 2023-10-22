package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task2Test {
    private Task2 task;

    @BeforeEach
    void setUp() {
        task = new Task2();
    }

    @Test
    void test1() {
        Task2.Rectangle rect = new Task2.Rectangle(10, 20);
        Assertions.assertEquals(200, rect.area());
    }

    @Test
    void test2() {
        Task2.Rectangle rect = new Task2.Square(10);
        Assertions.assertEquals(100, rect.area());
    }
}
