package com06_BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//该类主要演示一下阻塞队列对象的各种方法
//1、添加方法：add offer put 如果满了，直接报错，直接结束，一直阻塞
//2、移除方法：remove poll take 如果没有了，直接报错，直接结束，一直阻塞
//3、检查方法：element peek 不可用，如果没有，直接报错，直接结束，没有这个方法
public class Demo01_BlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(4);
        /*blockingQueue.put("哈哈");
        blockingQueue.put("嘿嘿");
        blockingQueue.put("呵呵");
        blockingQueue.put("呵呵");
        blockingQueue.put("呵呵");*/
        /*blockingQueue.add("哈哈");
        blockingQueue.add("嘿嘿");
        blockingQueue.add("呵呵");
        blockingQueue.add("呵呵");
        blockingQueue.remove("呵呵");*/
        /*blockingQueue.offer("哈哈");
        blockingQueue.offer("嘿嘿");
        blockingQueue.offer("呵呵");
        blockingQueue.offer("呵呵");
        blockingQueue.offer("呵呵");*/
//        blockingQueue.add("haha");
//        blockingQueue.remove();
//        blockingQueue.poll();
//        blockingQueue.take();
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue);
    }
}
