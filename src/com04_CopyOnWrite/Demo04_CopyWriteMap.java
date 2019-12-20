package com04_CopyOnWrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Demo04_CopyWriteMap {
    public static void main(String[] args) {
        //HashMap也是线程不安全的
        //Map map = new HashMap();
        //Map map = Collections.synchronizedMap(new HashMap<>());
        Map map = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            final int tempI = i;
            new Thread(()->{
                map.put(tempI,UUID.randomUUID().toString().substring(0,6));
                System.out.println(map.toString());
            },String.valueOf(i)).start();
        }

    }
}
