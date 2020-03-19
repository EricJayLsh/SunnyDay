package com.java8.impl;

import com.java8.functionInterface.FunctionInterface01;

public class Test {
    public static void main(String[] args) {

        // Function01 function01 =  new Function01();

        FunctionInterface01 functionInterface01 = new FunctionInterface01() {
            @Override
            public void test01() {
                System.out.println("test01");
            }

            @Override
            public void defaultFunction01() {
                System.out.println("defaultFunction01");
            }

            @Override
            public void defaultFunction02() {
                System.out.println("defaultFunction02");
            }
        };
        functionInterface01.test01();
        functionInterface01.defaultFunction01();
        functionInterface01.defaultFunction02();

        System.out.println("===========");

        FunctionInterface01 function01 = new Function01();
        FunctionInterface01 function02 = Function01::new;

        function01.test01();
        function01.defaultFunction01();
        function01.defaultFunction02();
        System.out.println("===========");

        FunctionInterface01 functionInterface02 = () -> System.out.println("lambada test01");
        FunctionInterface01 functionInterface03 = System.out::println;
        functionInterface03.test01();
        functionInterface02.test01();


    }
}
