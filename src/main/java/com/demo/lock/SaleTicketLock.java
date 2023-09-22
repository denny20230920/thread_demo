package com.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过lock接口的实现类，对多线程调用加锁
 */
public class SaleTicketLock {

    public static void main(String[] args) {

        SaleTicket saleTicket = new SaleTicket();

        Thread t1 = new Thread(saleTicket);
        Thread t2 = new Thread(saleTicket);
        Thread t3 = new Thread(saleTicket);
        Thread t4 = new Thread(saleTicket);
        Thread t5 = new Thread(saleTicket);
        Thread t6 = new Thread(saleTicket);

        t1.setName("售票窗口1");
        t2.setName("售票窗口2");
        t3.setName("售票窗口3");
        t4.setName("售票窗口4");
        t5.setName("售票窗口5");
        t6.setName("售票窗口6");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }

}

//卖票业务
class SaleTicket implements Runnable{

    //总共票数100张
    int tickets = 100;
    //标记
    boolean isFlag = true;

    //创建Lock的实例，需要确保多个线程共用同一个Lock实例！需要考虑将此对象声明为static final
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (isFlag){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try{
                //执行lock方法，锁定对共享资源的调用
                lock.lock();//代码块操作前加锁
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName()+"售票，票号："+tickets);
                    tickets--;
                }else {
                    isFlag = false;
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                //执行unlock()方法，代码执行后解锁
                lock.unlock();//代码块操作后解锁，无论怎样解锁是要执行的
            }
        }
    }
}
