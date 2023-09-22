package com.demo.single;

/**
 * 饿汉式单例模式   线程调用前已经创建好了对象，不存在线程安全问题
 */
public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){

    }

    public static EagerSingleton getInstance(){
        return instance;
    }

}
