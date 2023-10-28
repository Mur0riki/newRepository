package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {
    private Task2 task;

    @BeforeEach
    void setUp() {
        task = new Task2();
    }
    @Test
    void test1(){
        List<String> answer = task.bracketClustering("()()()");
        Assertions.assertEquals(answer, new ArrayList<String>(Arrays.asList("()", "()", "()")));
    }
    @Test
    void test2(){
        List<String> answer = task.bracketClustering("((()))");
        Assertions.assertEquals(answer, new ArrayList<String>(Arrays.asList("((()))")));
    }
    @Test
    void test3(){
        List<String> answer = task.bracketClustering("((()))(())()()(()())");
        Assertions.assertEquals(answer, new ArrayList<String>(Arrays.asList("((()))", "(())", "()", "()", "(()())")));
    }
    @Test
    void test4(){
        List<String> answer = task.bracketClustering("((())())(()(()()))");
        Assertions.assertEquals(answer, new ArrayList<String>(Arrays.asList("((())())", "(()(()()))")));
    }
    @Test
    void test5() throws RuntimeException{
        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            task.bracketClustering("a((())())(()(()()))");
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void test6() throws RuntimeException{
        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            task.bracketClustering("(()");
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void test7() throws RuntimeException{
        Throwable thrown = assertThrows(RuntimeException.class, () -> {
            task.bracketClustering("())");
        });
        assertNotNull(thrown.getMessage());
    }
}
