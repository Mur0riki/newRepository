package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    private Task1 task;

    @BeforeEach
    void setUp() {
        task = new Task1();

    }

    @Test
    void secMore60() {
        int length = task.videoLengthToSeconds("1:61");
        assertEquals(-1, length);
    }

    @Test
    void secLess60() {
        int length = task.videoLengthToSeconds("1:-1");
        assertEquals(-1, length);
    }

    @Test
    void AllisFine() {
        int length = task.videoLengthToSeconds("10:25");
        assertEquals(625, length);
    }

    @Test
    void test4() {
        int length = task.videoLengthToSeconds("0:1");
        assertEquals(1, length);
    }

    @Test
    void test5() {
        int length = task.videoLengthToSeconds("01");
        assertEquals(-1, length);
    }

}
