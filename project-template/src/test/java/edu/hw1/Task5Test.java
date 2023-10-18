package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {

    private Task5 task;

    @BeforeEach
    void setUp() {
        task = new Task5();
    }

    @Test
    void test1() {
        boolean flag = task.isPalindrome(11211230);
        assertTrue(flag);
    }

    @Test
    void test2() {
        boolean flag = task.isPalindrome(13001120);
        assertTrue(flag);
    }

    @Test
    void test3() {
        boolean flag = task.isPalindrome(23336014);
        assertTrue(flag);
    }

    @Test
    void test4() {
        boolean flag = task.isPalindrome(11);
        assertTrue(flag);
    }

    @Test
    void test5() {
        boolean flag = task.isPalindrome(1);
        assertFalse(flag);
    }

    @Test
    void test6() {
        boolean flag = task.isPalindrome(132);
        assertFalse(flag);
    }

    @Test
    void test7() {
        boolean flag = task.isPalindrome(5678);
        assertFalse(flag);
    }
}
