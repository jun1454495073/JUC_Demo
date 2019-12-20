package com01_Synchronize_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tick{
    private int tick = 30;
    Lock lock = new ReentrantLock();
    public void saleTick(){
        lock.lock();
        try {
            if (tick>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出去一张票，还剩下："+(--tick)+"张票");
            }
        }finally {
            lock.unlock();
        }
    }
}
public class Demo02_卖票_Lock {
    public static void main(String[] args) {
        Tick tick = new Tick();
        new Thread(()->{ for (int i = 0; i < 30; i++) tick.saleTick(); }, "A").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) tick.saleTick(); }, "B").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) tick.saleTick(); }, "C").start();
    }
}
