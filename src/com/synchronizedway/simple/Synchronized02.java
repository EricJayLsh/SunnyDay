package com.synchronizedway.simple;

/**
 * Author:Lenovo
 * <p>
 * 对象锁 1.代码块 2.普通方法
 * <p>
 * Date:2019/3/8
 **/
public class Synchronized02 implements Runnable {

    //  注意此处和类锁的区别
    static Synchronized02 synchronized02 = new Synchronized02();
    static int i = 0;
    Object lock = new Object();
    Object lock1 = new Object();
    Object lock2 = new Object();

    public static void main(String[] args) {

        // 创建两个线程
        Thread thread1 = new Thread(synchronized02);
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

       // synchronized (this) {
        synchronized (lock) {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            try {
                for (int j = 0; j < 100000; j++) {
                    i++;
                }
                 Thread.sleep(1000);
               // wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }

        synchronized (lock1) {
            System.out.println("当前线程名称lock1：" + Thread.currentThread().getName());
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "lock1运行结束");
        }

        synchronized (lock2) {
            System.out.println("当前线程名称lock2：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "lock2运行结束");
        }

        // 同步方法调用
        method();
    }



    public synchronized void method(){
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
