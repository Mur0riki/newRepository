package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    void test1() {
        List numbers = List.of(1, 2, 3);
        BackwardIterator iterator = new BackwardIterator<>(numbers);
        for (int i = 3; i > 0 ; i--) {
            if(iterator.hasNext());
            Assertions.assertEquals(i,iterator.next());
        }
    }
}
