package com.demo.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建两个线程，通过线程池的方式去调用实例线程
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
        service1.setMaximumPoolSize(50);//设置线程池中线程数的上限

        //2.执行指定的线程的操作，需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new A());//适合适用于Runnable
        service.execute(new B());//适合适用于Runnable

        //service.submit(Callable callable);//适合使用与Callable
        //3.关闭连接池
        service.shutdown();

    }

}


class A implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+",执行了方法A");
    }
}

class B implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+",执行了方法B");
    }
}