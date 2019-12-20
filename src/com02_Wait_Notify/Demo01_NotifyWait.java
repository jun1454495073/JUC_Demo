package com02_Wait_Notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//两个线程打印，来回打印，生产者和消费者模式
//此程序用的是Lock和Condition中的await和singleAll三角，
//也可以用synchrozied和Object中的await以及NotifyAll铁三角
//并且一定要注意在循环的时候，不能用if去判断，if只是判断一次，可能会出现虚假唤醒，我们应该用While

class Ziyuan{
    private boolean flag = true;//true代表现在消费者可以消费，蛋糕有一个存货
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void eat(){
        //消费者，需要加锁么？需要，因为要是全是吃货来抢着吃，吃的太难了
        lock.lock();
        try {
            while (!flag){
                //如果！flag也就是说没有存货了，此时，消费者线程应该等待
                condition.await();
            }
            //如果没有等待，就继续吃
            System.out.println(Thread.currentThread().getName()+"吃了应该蛋糕");
            //吃完了之后要告诉别人我吃完了你们可以继续生产了
            flag = false;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void shangchang(){
        //生产者线程所需要调用的方法
        lock.lock();
        try {
            while (flag){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"又生产了一个蛋糕");
            flag = true;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class Demo01_NotifyWait {

    public static void main(String[] args) {
        Ziyuan ziyuan = new Ziyuan();
        //创建两个线程，应该生产者应该消费者
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ziyuan.eat();
            }
        }, "消费者").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ziyuan.shangchang();
            }
        }, "生产者").start();
    }

}
