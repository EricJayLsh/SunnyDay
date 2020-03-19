package com.java8.dto;

import java.util.Objects;
import java.util.Random;

public class Persion implements Comparable<Persion> {
    private String name;
    private int age;
    private String sex;


    public Persion() {

    }

    public Persion(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public Persion setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Persion setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Persion setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persion)) return false;
        Persion persion = (Persion) o;
        return getAge() == persion.getAge() &&
                Objects.equals(getName(), persion.getName()) &&
                Objects.equals(getSex(), persion.getSex());
    }

    @Override
    public int hashCode() {
        return new Random().nextInt(10);
        //return Objects.hash(getName(), getAge(), getSex());
    }

    @Override
    public int compareTo(Persion o) {
        return this.getAge() - o.getAge();
    }
}
