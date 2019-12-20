package com01_Synchronize_Lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Piao03{
    private int ticket = 30;

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {

        this.ticket = ticket;
    }

    Lock lock = new ReentrantLock();
    public int saleTiket(){
        lock.lock();
        try {
            if (ticket>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出去一张票，还剩下："+(--ticket)+"张票");
            }else{
                System.out.println("票卖完了");
            }
            return ticket;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return ticket;
    }
}
public class Demo03_卖票_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Piao03 piao03 = new Piao03();

        FutureTask futureTask = new FutureTask(()->{
            int num = 0;
            for (int i = 0; i < 10; i++) {
                num = piao03.saleTiket();
            }
            return num;
        });

        //new Thread(futureTask,"C").start();不用
        new Thread(futureTask,"A").start();
        new Thread(new FutureTask(()->{
            int num = 0;
            for (int i = 0; i < 10; i++) {
                num = piao03.saleTiket();
            }
            return num;
        }),"B").start();
        new Thread(new FutureTask(()->{
            int num = 0;
            for (int i = 0; i < 10; i++) {
                num = piao03.saleTiket();
            }
            return num;
        }),"C").start();
        //这个需要等所有的线程（也就是所有的窗口卖完了票才能告诉别人还剩下几张票）

    }
}
