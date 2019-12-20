package com06_BlockingQueue;

import java.util.concurrent.SynchronousQueue;

public class Demo03_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
//        synchronousQueue.add("ha");
        synchronousQueue.offer("x");
        System.out.println(synchronousQueue);
        /*synchronousQueue.add("xi");
        System.out.println(synchronousQueue);*/
    }
}
