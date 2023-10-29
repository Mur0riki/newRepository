package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task6Test {

    @Test
    void test1(){
        MosByrzha byrzha;
        byrzha = new MosByrzha();
        byrzha.add(new Stock(100,"GSZ"));
        byrzha.add(new Stock(200,"GPS"));
        byrzha.add(new Stock(50,"GZX"));
        Assertions.assertEquals(200, byrzha.mostValuableStock().getValue());
    }
}
