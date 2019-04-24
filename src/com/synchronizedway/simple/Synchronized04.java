package com.synchronizedway.simple;

/**
 * Author:Lenovo
 * <p>
 * 1.两个线程同时访问一个对象的同步方法。 例子 Synchronized02
 * <p>
 * 2.两个线程访问的是两个对象的同步方法。（相当于两个实例的各自同步方法，则互不影响 可以并行执行）
 * <p>
 * 3.两个线程访问的是 synchronized 的静态方法。(类锁 则无法并行)
 * <p>
 * 4.同时访问同步方法与非同步方法(当前类中就是举例 只有一个方法加锁 则不会对其他方法造成影响 所以可以同步执行)
 * <p>
 * 5.访问同一个对象的不同的普通同步方法。(不同方法均加锁 则无法同步执行呀)
 * <p>
 * 6.同时访问静态synchronize和非静态的synchronized方法。(这是一个最难思考的问题 仔细想想)
 * <p>
 * 7.方法抛异常后，会释放锁(synchronized  会自动释放  lock 需要手动释放)
 * <p>
 * <p>
 * <p>
 * 1.一把锁只能被一个线程获取，没有拿到锁的线程必须等待；
 * <p>
 * 2.每个实例都对应有自己的一把锁，不同实例之间互不影响；例外： 锁对象是*.class以及synchronized修饰的的是static方法的时候，所有对象公用一把锁；
 * <p>
 * 3.无论是方法正常运行完毕或者方法抛出异常，都会释放锁。
 * <p>
 * 1、锁互斥，不共用
 * <p>
 * 2、不同锁不相互影响
 * <p>
 * 3、补充：synchronized方法里用了没被synchronized修饰的方法，是不线程安全的
 * <p>
 * synchronized 的性质 ：可重入性(获得锁之后可以直接再次使用)
 * 可重入：一个线程拿到了锁，这个线程可以再次使用该锁对其他方法，说明该锁是可以重入的
 * <p>
 * 不可重入：一个线程拿到锁了，如果需要再次使用该锁，必须先释放该锁才能再次获取
 * <p>
 * synchronized是可重入锁
 * <p>
 * 可重入锁的好处：
 * <p>
 * 1 避免死锁 2 提升封装性
 * Date:2019/3/8
 **/
public class Synchronized04 implements Runnable {

    // 创建两个不同的对象
    static Synchronized04 synchronized01 = new Synchronized04();


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
            // 非同步方法
            method2();

            // 同步方法2
            method3();
        }
    }


    // 同步方法1
    public synchronized void method() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            // wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    // 非同步方法
    private void method2() {
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
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


    public synchronized void method5() {
        System.out.println("这是一个非父类的方法");
    }

}
