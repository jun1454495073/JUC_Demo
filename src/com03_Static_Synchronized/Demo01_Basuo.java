package com03_Static_Synchronized;

import java.util.concurrent.TimeUnit;

class Phone1{
    //主要测试同步方法和静态同步方法以及普通方法在加锁之后的执行顺序
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("***Email***");
    }

    public synchronized void sendSMS(){
        System.out.println("***sendSMS***");
    }

    public void sayHello(){
        System.out.println("***Hello***");
    }

}

public class Demo01_Basuo {
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone1 = new Phone1();
        Phone1 phone2 = new Phone1();
        //创建两个线程去调用资源类
        new Thread(()->{
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //这里是故意设置主线程main睡300ms确保肯定是sendEmail比sendSMS，让我们能知道先抢到的是SendEmail，以此来分析
        Thread.sleep(300);
        new Thread(()-> {
            //phone1.sendSMS();
            //phone1.sayHello();
            phone2.sendSMS();
        }).start();
    }
}
/*
* 1、第一种情况是两个方法都是同步锁，一个资源类，两个线程去抢资源类中的不同的两个同步方法
*   结论：同步方法锁住的是this这个对象，谁调用的谁就被锁住了，两个线程都是操作一个资源类，只有一个资源类的对象
*         所以谁先抢到，谁就先执行！,就算是先抢到的那个抢到之后得瑟的睡一会，另一个也得乖乖的等着
* 2、第二种情况是一个资源类中一个同步方法一个普通方法，两个线程调用一个资源类的对象
*   结论：锁住的也是资源类的对象，
* 3、第三种情况是有两个资源类对象，两个线程分别操作资源类中不同的方法或者相同的也行
*   结论：此时锁锁住的是资源类对象，但是现在有两个资源类对象也就是互不干扰了，所以谁先执行完就算谁先输出
* 4、第四种情况是应该资源类中有两个静态同步方法，两个线程操作员工资源类对象的不同方法
*   结论：此时是静态同步方法，所以锁的是整个Class模板，也就是整个资源类，所以谁先抢到谁先执行
* 5、第五种情况是资源类中有两个静态同步方法，两个线程调用两个不同的资源类对象
*   结论：此时锁的是整个Class模板，但是因为是两个资源类，所以也是谁先执行就是谁
* 6、一个普通静态方法一个同步方法，一个资源类对象
*   结论：SMS可是资源类就像手机，资源类中的普通方法就像是手机壳，不一定谁先抢到先就先完
*         成运行，应该是谁运行的快谁先运行，互不干扰。
* 7、一个普通静态方法一个半同步方法，两个资源类对象
*   结论：SMS
*
*/
