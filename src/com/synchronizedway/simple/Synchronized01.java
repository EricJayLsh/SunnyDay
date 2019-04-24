package com.synchronizedway.simple;

/**
 * Author:Lenovo
 * <p>
 * synchronized 可以保证同一时刻只有一个线程执行该段代码 达到并发安全的作用
 *
 * 对象锁：1.方法锁 （Synchronized 修饰的方法）2.同步代码块锁（指定所对象）
 * 类锁：1.静态方法锁 2.对象为class对象
 * <p>
 * Date:2019/3/8
 **/
public class Synchronized01 implements Runnable {

    static Synchronized01 synchronized01 = new Synchronized01();

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        // 创建两个线程 共用一个实例 （类对象）
        Thread thread1 = new Thread(synchronized01);
        Thread thread2 = new Thread(synchronized01);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("两个线程都执行结束了");
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
        System.out.println("当前线程名称：" + Thread.currentThread().getName());

    }
}






