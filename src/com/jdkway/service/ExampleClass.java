package com.jdkway.service;

/**
 * Author:Lenovo
 * 一个简单的类 为了说明接口中的实例方法和类中的方法名相同时 优先调用类中的方法
 * Date:2019/4/4
 **/
public class ExampleClass {

    public String defaultFunction() {
        return "这是一个类中定义的方法 和接口中的方法同名 调用时会优先使用类中的方法";
    }

}
