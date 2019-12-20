package com05_Three_JUCClass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//该类主要是介绍Semaphore工具类，信号灯
//例子：停车场停车位的例子
//原理：需要定义一个信号量，在信号量上我们定义了两种操作
//1、acquire（获取）当一个线程调用acquice操作时，它要么通过成功获取信号量（信号量减1）
//2、release（释放）实际上会将信号量的值加1，然后唤醒等待的线程
//3、信号量的目的：一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
public class Demo03_Semaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);//五个车位？？？
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                try {
                    boolean flag = false;
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    //也就是说明这个车停在停车场停了3秒
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
