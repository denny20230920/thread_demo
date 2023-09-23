package com.demo.callable;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) {

        //3.创建Callable接口实现类的对象
        NumPrintCount numPrintCount = new NumPrintCount();

        //4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numPrintCount);

        //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创造Thread对象，并调用start()
        Thread thread = new Thread(futureTask);

        thread.start();

        try {
            //6.获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值
            int sum = (int) futureTask.get();
            System.out.println("总和为："+sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

//1.创建一个实现Callable的实现类
class NumPrintCount implements Callable{
    //2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0){
                System.out.println("偶数值为："+i);
                //统计所有偶数值的和
                sum += i;
            }
            Thread.sleep(50);
        }
        return sum;
    }
}