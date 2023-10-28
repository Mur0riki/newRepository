package edu.hw3;

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
        String answer = task.atbachCipher("Hello world!");
        Assertions.assertEquals("Svool dliow!",answer);
    }
    @Test
    void test2(){
        String answer = task.atbachCipher("abcdefghijklmnopqrstuvwxyz 123-'. ABCDEFGHIJKLMNOPRSTUVWXYZ");
        Assertions.assertEquals("zyxwvutsrqponmlkjihgfedcba 123-'. ZYXWVUTSRQPONMLKIHGFEDCBA",answer);
    }
    @Test
    void test3(){
        String answer = task.atbachCipher("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler");
        Assertions.assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",answer);
    }

}
