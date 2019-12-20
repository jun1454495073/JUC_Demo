package com04_CopyOnWrite;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//此类主要讲解的是ArrayList的写时复制
public class Demo02_CopyWriteArrayList {
    public static void main(String[] args) {
        //ArrayList是线程不安全的，以前我们使用时都是单线程，此时我们来使用多线程
        //会出现并发修改异常：ConcurrentModificationException
        //出现这个异常的原因：sout(list)中的list默认实现了tostring方法，版本号不一样
        //解决的办法：Vector/Collections中的Synchronized
        //List list = new ArrayList();
        //List list = new Vector();
        //List list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list.toString());
            },String.valueOf(i)).start();
        }
    }
    @Test
    public void test(){
        System.out.println("adasfasd");
    }
}
