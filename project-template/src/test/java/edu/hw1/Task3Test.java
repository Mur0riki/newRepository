package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    private Task3 task;

    @BeforeEach
    void setUp() {
        task = new Task3();

    }

    @Test
    void isNestable() {
        boolean flag = task.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6});
        assertTrue(flag);
    }

    @Test
    void isnotNestable() {
        boolean flag = task.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertFalse(flag);
    }

}
