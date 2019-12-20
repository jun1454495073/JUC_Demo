package com01_Synchronize_Lock;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Piao05{
    private int ticket=30;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void sale(){
        readWriteLock.writeLock().lock();
        try {
            if (ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖出一张，还剩："+(--ticket)+"张");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void getTicket(){
        readWriteLock.readLock().lock();
        try {
            System.out.println("剩余票数："+ticket+"张，请大家放心购买！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

}
public class Demo05_卖票_ReadWriteLock {
    public static void main(String[] args) {
        Piao05 piao05 = new Piao05();
        //先不创建工具类,事实证明不可行，然后我们来创建
       // CyclicBarrier cyclicBarrier = new CyclicBarrier(3,
             //   ()->{piao05.getTicket();});
        //创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                3,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //总共有几个人买票就是有几个线程买票，窗口是线程池的最大线程数
        try {
            for (int i = 0; i < 20; i++) {
                executorService.execute(()->{
                    piao05.sale();
                    //cyclicBarrier.await();
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
            piao05.getTicket();
        }
//        piao05.getTicket();
    }
}
