package com.demo.thread;

/**
 * 通过实现Runnable接口，创建多线程
 */
public class PringNumber {

    public static void main(String[] args) {

        //调用实现Runnable的类OtherNumberPrint,来实现多线程
        new Thread(new OtherNumberPrint()).start();

        //调用实现Runnable的类EvenNumberPrint，来实现多线程
        new Thread(new EvenNumberPrint()).start();

    }

}

class OtherNumberPrint implements Runnable{
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10000; i++) {
            if (i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+",当前线程获取的质数值："+i);
            }
        }
    }
}


class EvenNumberPrint implements Runnable{
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+",当前线程获取的偶数值："+i);
            }
        }
    }
}