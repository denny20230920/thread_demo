package com.demo.other;

/**
 * 案例1，使用两个线程打印1~100.线程1和线程2交替打印
 */
public class NumberPrint {

    public static void main(String[] args) {

        Print print = new Print();

        Thread t1 = new Thread(print,"线程一");
        Thread t2 = new Thread(print,"线程二");

        t1.start();
        t2.start();

    }

}


//打印1-100的类
class Print implements Runnable{

    private int number = 100;

    @Override
    public void run() {


        while (true){

            synchronized (this){
                if (number > 0) {

                    notify();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ",打印数：" + number);
                    number--;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    break;
                }
            }

        }

    }
}