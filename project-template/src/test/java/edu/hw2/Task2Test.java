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
        Rectangle rect = new Rectangle(10, 20);
        Assertions.assertEquals(200, rect.area());
    }

    @Test
    void test2() {
        Rectangle rect = new Square(10);
        Assertions.assertEquals(100, rect.area());
    }
}
