package com.synchronizedway.simple;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Author:Lenovo
 * <p>
 * 类锁 1.static 方法上 2. （*.class）
 * <p>
 * class 类对象只有一个，即不管该类有多少个实例，其class对象均相同
 * <p>
 * Date:2019/3/8
 **/
public class Synchronized03 implements Runnable {

    // 创建两个不同的对象
    static Synchronized03 synchronized01 = new Synchronized03();
    static Synchronized03 synchronized02 = new Synchronized03();

    static int i = 0;

    public static void main(String[] args) {
        // 创建两个线程
        Thread thread1 = new Thread(synchronized01);
        Thread thread2 = new Thread(synchronized02);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("两个线程都执行结束了");
        System.out.println(i);
    }


    @Override
    public void run() {

        //method();

        method2();
    }

    // 注意此处的 static 和不加static 的区别（因为两个线程是不同的实例 则可以对方法进行同步访问 但是加了static之后是类方法 两个实例又是属于同一个对象 则不能够并行访问）
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

    private void method2() {

        // 注意此处
        synchronized (Synchronized03.class) {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                // wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }


}
