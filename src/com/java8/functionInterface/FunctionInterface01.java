package com.java8.functionInterface;

@FunctionalInterface
public interface FunctionInterface01 {

    void test01();

    default void defaultFunction01() {
        System.out.println("default01方法");
    }

    default void defaultFunction02() {
        System.out.println("default02方法");
    }

    static void staticFunction01() {
        System.out.println("static01方法");
    }

    static void staticFunction02() {
        System.out.println("static02方法");
    }

    boolean equals(Object obj);

    String toString();


}
