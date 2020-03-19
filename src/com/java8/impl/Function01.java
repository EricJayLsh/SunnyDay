package com.java8.impl;

import com.java8.functionInterface.FunctionInterface01;

public class Function01 implements FunctionInterface01 {

    public Function01(){}

    @Override
    public void test01() {
        System.out.println("实现接口test01方法");
    }

    @Override
    public void defaultFunction01() {
        System.out.println("重写defaultFunction01");
    }

    @Override
    public void defaultFunction02() {
        System.out.println("重写defaultFunction02");
    }
}
