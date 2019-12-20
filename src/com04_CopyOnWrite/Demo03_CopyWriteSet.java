package com04_CopyOnWrite;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo03_CopyWriteSet {
    public static void main(String[] args) {
        //HashSet也是线程不安全的
        //Set set = new HashSet();
        //Set set = Collections.synchronizedSet(new HashSet<>());
        Set set = new CopyOnWriteArraySet();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(set.toString());
            },String.valueOf(i)).start();
        }
    }
}
