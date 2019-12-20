package com01_Synchronize_Lock;

//口诀：线程 操作 资源类
//版本01：最基本的synchronized写的三个窗口卖30张票的题目
class TicketTest{
    private int ticket = 30;
    private int num = 1;
    public synchronized void saleTicket(){
        if (ticket>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出去了一张票，还剩下："+(--ticket)+" 张票");
        }
        System.out.println("票卖完了");
    }
}
public class Demo01_卖票_Synchronzied {
    public static void main(String[] args) {
        TicketTest ticketTest = new TicketTest();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticketTest.saleTicket();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticketTest.saleTicket();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticketTest.saleTicket();
            }
        }, "C").start();
    }
}
