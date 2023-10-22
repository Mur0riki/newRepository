package edu.hw2;

import edu.hw1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task1Test {
    private Task1 task;
    @BeforeEach
    void setUp() {
         task = new Task1();
    }

    @Test
    void test1(){
        var two = new edu.hw2.Task1.Expr.Constant(2);
        Assertions.assertEquals(2,two);
    }

    @Test
    void test2(){
        var negOne = new edu.hw2.Task1.Expr.Negate(new edu.hw2.Task1.Expr.Constant(1));
        Assertions.assertEquals(-1,negOne);
    }

    @Test
    void test3(){

    }
}
