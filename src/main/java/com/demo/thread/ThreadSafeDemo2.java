package com.demo.thread;

/**
 * 模拟六个窗口卖100张票；会出现重票和错票
 */
class TicketSaleWindow1 implements Runnable{

    //总票量
    int ticketCout = 100;
    //标记
    boolean isFlag = true;

    @Override
    public void run() {

        while (isFlag){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            show();

        }

    }

    public synchronized void show(){//默认的使用this，作为同步监视器

        if (ticketCout > 0){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+",票号为："+ticketCout);
            ticketCout--;

        }else {
            isFlag = false;
        }
    }
}

public class ThreadSafeDemo2 {

    public static void main(String[] args) {

        TicketSaleWindow1 ticketSaleWindow1 = new TicketSaleWindow1();

        Thread tt1 = new Thread(ticketSaleWindow1);
        Thread tt2 = new Thread(ticketSaleWindow1);
        Thread tt3 = new Thread(ticketSaleWindow1);
        Thread tt4 = new Thread(ticketSaleWindow1);
        Thread tt5 = new Thread(ticketSaleWindow1);
        Thread tt6 = new Thread(ticketSaleWindow1);

        tt1.setName("售票窗口1");
        tt2.setName("售票窗口2");
        tt3.setName("售票窗口3");
        tt4.setName("售票窗口4");
        tt5.setName("售票窗口5");
        tt6.setName("售票窗口6");

        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();
        tt5.start();
        tt6.start();
    }

}