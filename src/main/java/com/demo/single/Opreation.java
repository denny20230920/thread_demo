package com.demo.single;

public class Opreation {

    public static void main(String[] args) {

        //实例化多个懒汉模式
        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton1 == lazySingleton2);

        //实例化多个饿汉模式
        EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();
        EagerSingleton eagerSingleton2 = EagerSingleton.getInstance();
        System.out.println(eagerSingleton1 == eagerSingleton2);
    }

}
