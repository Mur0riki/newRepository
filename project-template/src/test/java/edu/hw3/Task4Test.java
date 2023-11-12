package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task4Test {
    private Task4 task;

    @BeforeEach
    void setUp() {
        task = new Task4();
    }

    @Test
    void test1(){
        Assertions.assertEquals(task.intToRoman(2),"II");
    }
    @Test
    void test2(){
        Assertions.assertEquals(task.intToRoman(12),"XII");
    }
    @Test
    void test3(){
        Assertions.assertEquals(task.intToRoman(16),"XVI");
    }
}
