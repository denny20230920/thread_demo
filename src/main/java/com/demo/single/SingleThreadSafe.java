package com.demo.single;

/**
 * 懒汉式单例模式的线程安全问题
 */
public class SingleThreadSafe {

   static LazySingleton lazySingleton1 = null;
   static LazySingleton lazySingleton2 = null;

    public static void main(String[] args)  {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               lazySingleton1 = LazySingleton.getInstance();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lazySingleton2 = LazySingleton.getInstance();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(lazySingleton1);
        System.out.println(lazySingleton2);
        System.out.println(lazySingleton1 == lazySingleton2);

    }

}
