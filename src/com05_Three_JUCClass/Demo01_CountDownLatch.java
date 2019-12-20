package com05_Three_JUCClass;

import java.util.concurrent.CountDownLatch;

//该类主要用于减少计数
//例子：当教室的所有学生都走了之后，值日生才可以打扫卫生然后锁门
//原理：CountDownLatch主要有两个方法：
//1、当一个线程或者多个线程调用await方法时，这些线程会阻塞
//2、其它线程调用countDown方法会将计数器减1（调用countDown方法的线程不会阻塞）
//3、当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行
public class Demo01_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            final int tempI = i;
            new Thread(()->{
                System.out.println("学号为"+tempI+"的学生离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("锁门");
    }
}
