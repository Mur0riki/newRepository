package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw3.NullKeyTreeMapDemo.createNullHandlingComparator;

class Task7Test {
    @Test
    void test1() {
        TreeMap tree = new TreeMap<>(createNullHandlingComparator());
        tree.put(null, "test");
        Assertions.assertTrue(tree.containsKey(null));
    }
}
