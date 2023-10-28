package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task3Test {
    private Task3 task;

    @BeforeEach
    void setUp() {
        task = new Task3();
    }

    @Test
    void test1(){
        HashMap<String,Integer> expected = new HashMap<>();
        expected.put("bb",2);
        expected.put("a",2);
        List<String> list = new ArrayList<>(Arrays.asList("bb", "bb","a","a"));
        HashMap<Object, Integer> answer = task.freqDict(list);
        Assertions.assertEquals(expected,answer);
    }
    @Test
    void test2(){
        HashMap<Object,Integer> expected = new HashMap<>();
        expected.put(1,2);
        expected.put(2,2);
        List<Object> list = new ArrayList<>(Arrays.asList(1, 2,1,2));
        HashMap<Object, Integer> answer = task.freqDict(list);
        Assertions.assertEquals(expected,answer);
    }
    @Test
    void test3(){
        HashMap<Object,Integer> expected = new HashMap<>();
        expected.put("that",1);
        expected.put("and",2);
        expected.put("this",1);
        expected.put("код",1);
        List<Object> list = new ArrayList<>(Arrays.asList("that","and","this","and","код"));
        HashMap<Object, Integer> answer = task.freqDict(list);
        Assertions.assertEquals(expected,answer);
    }

}
