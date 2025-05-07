package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class MyTestsClass {

    @Before
    public void before() {
        System.out.println("Before each test method executed");
    }

    @After
    public void after() {
        System.out.println("After each test method executed\n");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 - Success");
    }

    @Test
    public void test2() {
        throw new RuntimeException("Test 2 - Failed");
    }

    @Test
    public void test3() {
        System.out.println("Test 3 - Success");
    }
}