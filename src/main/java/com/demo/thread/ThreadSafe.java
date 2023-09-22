package com.demo.thread;

/**
 * 模拟六个窗口卖100张票；会出现重票和错票
 */
class TicketSaleWindow implements Runnable{

    //总票量
    int ticketCout = 100;
    //实例一个唯一的对象，用户同步锁
//    Object object = new Object();
    Dog dog = new Dog();

    @Override
    public void run() {

        while (true){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //synchronized (object){ //object对象唯一？yes
            //synchronized (dog){//dogd对象唯一？yes
            synchronized (this){//this对象唯一？yes,谁调用对象它代表谁
                if (ticketCout > 0){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+",票号为："+ticketCout);
                    ticketCout--;
                }else {
                    break;
                }

            }
        }

    }
}

public class ThreadSafe {

    public static void main(String[] args) {

        TicketSaleWindow ticketSaleWindow = new TicketSaleWindow();

        Thread t1 = new Thread(ticketSaleWindow);
        Thread t2 = new Thread(ticketSaleWindow);
        Thread t3 = new Thread(ticketSaleWindow);
        Thread t4 = new Thread(ticketSaleWindow);
        Thread t5 = new Thread(ticketSaleWindow);
        Thread t6 = new Thread(ticketSaleWindow);

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

//看门狗，定义唯一对象进行同步锁
class Dog{

}