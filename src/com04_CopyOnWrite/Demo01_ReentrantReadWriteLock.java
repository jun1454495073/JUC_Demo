package com04_CopyOnWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MapTest{
    private Map<String,String> map = new HashMap<String,String>();

    //这个资源类也是需要加锁，但是需要我们加读写锁，而不是synchronized或者Lock
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        //这个方法是对我们资源类的数据进行修改，所以需要加写锁
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入数据开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入数据结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取数据开始");
            String s = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取数据结束，结果："+s);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

}

public class Demo01_ReentrantReadWriteLock {
    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        //创建两个一个并发读取数据，一个并发写数据
        for (int i = 0; i < 10; i++) {
            int tempI = i;
            new Thread(()->{
                mapTest.put(tempI+"",tempI+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            int tempI = i;
            new Thread(()->{
                mapTest.get(tempI+"");
            },String.valueOf(i)).start();
        }
    }
}
