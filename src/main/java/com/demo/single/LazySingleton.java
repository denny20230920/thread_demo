package com.demo.single;

/**
 * 懒汉式单例模式   线程执行调用时，会示例化对象存在线程安全问题
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){

    }

//    public synchronized static LazySingleton getInstance(){ //通过添加synchronized进行同步，解决安全问题
//
//        if (instance == null){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

//    public static LazySingleton getInstance(){ //通过添加synchronized进行同步，解决安全问题
//
//        synchronized (LazySingleton.class){
//
//            if (instance == null){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                instance = new LazySingleton();
//            }
//        }
//        return instance;
//    }

    public static LazySingleton getInstance(){ //通过添加synchronized进行同步，解决安全问题

        if (instance == null){//生成了一次实例后，后面的线程不需要再调用生成同样的单例了，所有判断不为空就不执行此方法
            synchronized (LazySingleton.class){
                if (instance == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
