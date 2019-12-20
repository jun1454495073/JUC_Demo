package com01_Synchronize_Lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class P{
    private int num = 30;
    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"卖出一张，还剩下："+(--num)+"张");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class Demo01 {
    public static void main(String[] args) {
        P p = new P();
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                2,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 30; i++) {
                executorService.execute(()->{
                    p.sale();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
