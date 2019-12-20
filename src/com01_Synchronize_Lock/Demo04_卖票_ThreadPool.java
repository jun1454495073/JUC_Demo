package com01_Synchronize_Lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Piao04{
    private int ticket = 60;

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {

        this.ticket = ticket;
    }

    Lock lock = new ReentrantLock();
    public void saleTiket(){
        lock.lock();
        try {
            if (ticket>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出去一张票，还剩下："+(--ticket)+"张票");
            }else{
                System.out.println("票卖完了");
            }
        }finally {
            lock.unlock();
        }
    }
}
public class Demo04_卖票_ThreadPool {
    public static void main(String[] args) {
        Piao04 piao04 = new Piao04();
        //创建一个线程池来买票
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                3,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(60),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //For循环里是有几个用户请求
        try {
            for (int i = 0; i < 60; i++) {
                executorService.execute(()->{
                    piao04.saleTiket();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
