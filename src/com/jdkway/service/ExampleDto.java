package com.jdkway.service;

/**
 * Author:Lenovo
 * 一个简单的实体类
 * Date:2019/4/4
 **/
public class ExampleDto {

    private String a;
    private String b;
    private String c;
    private Integer d;
    private String name;
    private Integer age;

    ExampleDto() {
        System.out.println("这是个无参数的构造方法");
    }

    ExampleDto(String a, String b, String c, Integer d, String name, Integer age) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
        this.age = age;

    }

    ExampleDto(Integer d) {
        System.out.println("这是有参数的构造方法" + d);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExampleDto{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d=" + d +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
