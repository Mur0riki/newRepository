package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task2Test {

    private Task2 task;

    @BeforeEach
    void setUp() {
        task =new Task2();

    }

    @Test
    void numberIsZero(){
        int count = task.countDigit(0);
        Assertions.assertEquals(1,count);
    }
    @Test
    void numberIsPositive(){
        int count = task.countDigit(15);
        Assertions.assertEquals(2,count);
    }

    @Test
    void numberIsNegative(){
        int count = task.countDigit(-15);
        Assertions.assertEquals(2,count);
    }

}
