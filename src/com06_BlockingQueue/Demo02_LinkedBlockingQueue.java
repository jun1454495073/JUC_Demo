package com06_BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Demo02_LinkedBlockingQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(3);
        System.out.println(linkedBlockingQueue.add("haha"));
        System.out.println(linkedBlockingQueue.remove());
        System.out.println(linkedBlockingQueue);
    }
}
