package com.java8.impl;

import com.java8.dto.Persion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static List<Persion> getPersion() {

        List<Persion> persions = new ArrayList<>();
        persions.add(new Persion("小红", 10, "女"));
        persions.add(new Persion("小黑", 12, "女"));
        persions.add(new Persion("小白", 5, "男"));
        persions.add(new Persion("小狼", 9, "男"));
        persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));
       // persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));
        return persions;
    }
    public static List<Persion> getPersion2() {

        List<Persion> persions = new ArrayList<>();
        persions.add(new Persion("小红", 10, "女"));
        persions.add(new Persion("小黑", 12, "女"));
        persions.add(new Persion("小白", 5, "男"));
        persions.add(new Persion("小狼", 9, "男"));
        persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));
        persions.add(new Persion().setName("小猪").setAge(15).setSex("男"));
        return persions;
    }
}
