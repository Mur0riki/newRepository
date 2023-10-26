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
        var two = new edu.hw2.Constant(2);
        Assertions.assertEquals(2,two.evaulate());
    }

    @Test
    void test2(){
        var negOne = new edu.hw2.Negate(new edu.hw2.Constant(1));
        Assertions.assertEquals(-1,negOne.evaulate());
    }

    @Test
    void test3(){
        var two = new edu.hw2.Constant(2);
        var four = new edu.hw2.Constant(4);
        var negOne = new edu.hw2.Negate(new edu.hw2.Constant(1));
        var sumTwoFour = new edu.hw2.Addition(two, four);
        var mult = new edu.hw2.Multiplication(sumTwoFour, negOne);
        var exp = new edu.hw2.Exponent(mult, 2);
        var res = new edu.hw2.Addition(exp, new edu.hw2.Constant(1));
        Assertions.assertEquals(37,res.evaulate());
    }
}
