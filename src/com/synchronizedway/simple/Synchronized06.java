package com.synchronizedway.simple;

/**
 * Author:Lenovo
 * <p>
 * synchronized 的性质 ：1.可重入性(获得锁之后可以直接再次使用  递归锁)  2. 不可中断（注意和lock不同）
 * 可重入：一个线程拿到了锁，这个线程可以再次使用该锁对其他方法，说明该锁是可以重入的
 * 不可重入：一个线程拿到锁了，如果需要再次使用该锁，必须先释放该锁才能再次获取
 * 可重入锁的好处：1 避免死锁 2 提升封装性
 *
 *
 * <p>
 * <p>
 * Date:2019/3/8
 **/
public class Synchronized06 extends Synchronized05 {

    int a = 0;

    public static void main(String[] args) {

        //  测试可重入的粒度 递归调用  synchronized 修饰的方法
        Synchronized06 synchronized01 = new Synchronized06();
        synchronized01.method0();

        // synchronized 方法内 调用另一个 synchronized 方法
        synchronized01.method1();

        synchronized01.method3();
    }

    public synchronized void method0() {
        System.out.println("a的值" + a);
        if (a == 0) {
            a++;
            method0();
        }
    }

    public synchronized void method1() {
        System.out.println("我是方法method1");
        method2();
    }

    public synchronized void method2() {
        System.out.println("我是方法method2");
    }

    //  调用其他类中的 synchronized 方法
    public synchronized void method3() {
        System.out.println("我是方法method3");

        super.method4();

        Synchronized04 synchronized04 = new Synchronized04();
        synchronized04.method5();


    }


}
