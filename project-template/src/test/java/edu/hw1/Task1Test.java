package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task1Test {
    private Task1 task;

    @BeforeEach
    void setUp() {
    task =new Task1();

}
    @Test
    void secMore60(){
        int length = task.videoLengthToSeconds("1:61");
        Assertions.assertEquals(-1,length);
    }
    @Test
    void secLess60(){
        int length = task.videoLengthToSeconds("1:-1");
        Assertions.assertEquals(-1,length);
    }
    @Test
    void AllisFine(){
        int length = task.videoLengthToSeconds("10:25");
        Assertions.assertEquals(625,length);
    }

    @Test
    void test4(){
        int length = task.videoLengthToSeconds("0:1");
        Assertions.assertEquals(1,length);
    }

}
