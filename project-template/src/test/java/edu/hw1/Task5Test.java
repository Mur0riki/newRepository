package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task5Test {

    private Task5 task;

    @BeforeEach
    void setUp() {
        task =new Task5();
    }

    @Test
    void test1(){
        boolean flag = task.isPalindrome(11211230);
        Assertions.assertTrue(flag);
    }
    @Test
    void test2(){
        boolean flag = task.isPalindrome(13001120);
        Assertions.assertTrue(flag);
    }
    @Test
    void test3(){
        boolean flag = task.isPalindrome(23336014);
        Assertions.assertTrue(flag);
    }
    @Test
    void test4(){
        boolean flag = task.isPalindrome(11);
        Assertions.assertTrue(flag);
    }
    @Test
    void test5(){
        boolean flag = task.isPalindrome(1);
        Assertions.assertFalse(flag);
    }
    @Test
    void test6(){
        boolean flag = task.isPalindrome(132);
        Assertions.assertFalse(flag);
    }
    @Test
    void test7(){
        boolean flag = task.isPalindrome(5678);
        Assertions.assertFalse(flag);
    }
}
