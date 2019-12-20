package com02_Wait_Notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ziyuan02{
    //标志位，用来判断该线程运行完之后该唤醒那个线程
    private int flag = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    //将三个线程需要执行的方法都合并成为了一个方法
    public void printMethod(){
        if (Thread.currentThread().getName()=="A"){
            //如果是A线程就将打印123
            lock.lock();
            try {
                while (flag!=1){
                    //不等于就是不应该是A线程打印，所以需要暂停A
                    condition1.await();
                }
                //然后如果没有进入if则就直接干活
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }//通知干活
                flag = 2;
                condition2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }else if (Thread.currentThread().getName()=="B"){
            //如果是A线程就将打印123
            lock.lock();
            try {
                while (flag!=2){
                    //不等于就是不应该是A线程打印，所以需要暂停A
                    condition2.await();
                }
                //然后如果没有进入if则就直接干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }//通知干活
                flag = 3;
                condition3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }else {
            //如果是A线程就将打印123
            lock.lock();
            try {
                while (flag!=3){
                    //不等于就是不应该是A线程打印，所以需要暂停A
                    condition3.await();
                }
                //然后如果没有进入if则就直接干活
                for (int i = 1; i <= 7; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }//通知干活
                flag = 1;
                condition1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
public class Demo02_ConditionHuLaQiu {
    public static void main(String[] args) {
        Ziyuan02 ziyuan02 = new Ziyuan02();
        new Thread(()->{ for (int i = 0; i < 5; i++) ziyuan02.printMethod(); }, "A").start();
        new Thread(()->{ for (int i = 0; i < 5; i++) ziyuan02.printMethod(); }, "B").start();
        new Thread(()->{ for (int i = 0; i < 5; i++) ziyuan02.printMethod(); }, "C").start();
    }
}
