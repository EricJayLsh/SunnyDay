package com.synchronizedway.simple;

/**
 * Author:Lenovo
 * 6.同时访问静态synchronize和非静态的synchronized方法。(这是一个最难思考的问题 仔细想想)
 * 类锁 锁的是class对象 ，而对象锁锁的是类的实例 则这两个锁不同 则可以同步执行
 * <p>
 * Date:2019/3/8
 **/
public class Synchronized05 implements Runnable {

    // 创建两个不同的对象
    static Synchronized05 synchronized01 = new Synchronized05();


    public static void main(String[] args) {
        // 创建两个线程
        Thread thread1 = new Thread(synchronized01);
        Thread thread2 = new Thread(synchronized01);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("两个线程都执行结束了");
    }


    @Override
    public void run() {

        if ("Thread-0".equals(Thread.currentThread().getName())) {
            // 同步方法
            method();
        } else {
            // 同步方法2
            method3();
        }
    }


    // 同步方法1
    public static synchronized void method() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            // wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    // 同步方法2
    public synchronized void method3() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            // wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }


    public synchronized void method4() {
        System.out.println("这是一个父类中的方法");
    }

}
