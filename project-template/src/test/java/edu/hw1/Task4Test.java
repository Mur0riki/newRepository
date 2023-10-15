package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task4Test {
    private Task4 task;

    @BeforeEach
    void setUp() {
        task =new Task4();
    }

    @Test
    void oddString(){
        String string = task.fixString("badce");
        Assertions.assertEquals("abcde",string);
    }

    @Test
    void evenString(){
        String string = task.fixString("badcef");
        Assertions.assertEquals("abcdfe",string);
    }

    @Test
    void string(){
        String string = task.fixString("hTsii  s aimex dpus rtni.g");
        Assertions.assertEquals("This is a mixed up string.",string);
    }

    @Test
    void emptyString(){
        String string = task.fixString("");
        Assertions.assertEquals("",string);
    }

    @Test
    void oneSymbolString(){
        String string = task.fixString(" ");
        Assertions.assertEquals(" ",string);
    }

}
