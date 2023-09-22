package com.demo.deadlock;

/**
 * 死锁问题演示和解决
 * 问题原因：线程1，依次产生了锁1和锁2，线程1要操作对象1和对象2
 *         线程2，依次产生了锁2和锁1，线程2要操作对象1和对象2
 *         线程1的第一个锁和线程2的第一个锁，调用的资源分别在对方的锁里，互相僵持导致死锁--程序不往下执行
 */
public class DeadLock {

    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        //线程1，调用了s1和s2对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                //第一个锁
                synchronized (s1){

                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s2){

                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }).start();

        //线程2，调用了s1和s2对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                //第一个锁
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s1){

                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }).start();

    }

}
