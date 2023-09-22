package com.demo.thread;

/**
 * 通过继承Thread类，创建多线程
 */

public class EvenNumber {

    public static void main(String[] args) {

        PrintNumber printNumber = new PrintNumber();
        printNumber.start();

        //输出10000以内的质数
        new Thread(){
            @Override
            public void run() {
                //获取质数
                for (int i = 0; i < 10000; i++) {
                    if (i%2 != 0){
                        System.out.println("当前运行的线程:"+Thread.currentThread().getName()+",获取的质数值为："+i);
                    }
                }
            }
        }.start();



    }

}

/**
 *        (1)创建一个继承Thread的子类
 *        (2)重写Thread类的run() ----> 将此线程要执行的操作，声明在此方法体中
 *        (3)创建当前Thread的子类对象
 *        (4)通过对象调用start()
 */
class PrintNumber extends Thread{

    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
            if (i%2 == 0){
                System.out.println("当前运行的线程:"+Thread.currentThread().getName()+",获取的偶数值为："+i);
            }
        }

    }
}
