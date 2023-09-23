package com.demo.other;

import javafx.geometry.Pos;

/**
 * 案例：生产者和消费者
 * 生产者(Productor)将产品交给店员(Clerk),而消费者(Customer)从店员处取走产品，店员一次只能持有
 * 固定数量的产品(比如20)，如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店总有空位放产品
 * 了在通知生产者继续生产，如果店中没有产品了，电汇会告诉消费者等一下，如果店中有产品了再通知消费者来
 * 取走产品。
 * 线程数：生产者、消费者
 */
public class ProductorCustor {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Product product = new Product(clerk);
        Custom custom = new Custom(clerk);

        Thread t1 = new Thread(product,"生产者");
        Thread t2 = new Thread(custom,"消费者");


        t1.start();
        t2.start();

    }

}

class Clerk{//店员

    private int productNum = 0;

    //增加产品数量
    public synchronized void addProductNum(){

        if (productNum >= 20){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            productNum++;
            System.out.println(Thread.currentThread().getName()+"，生产第"+productNum+"件商品");

            if (productNum == 20){
                notify();
            }
        }

    }

    //减少产品数量
    public synchronized void reduceProductNum(){

        if (productNum <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+"，消耗第"+productNum+"件商品");
            productNum--;

            if (productNum == 0){
                notify();
            }
        }

    }
}

class Product implements Runnable{

    private Clerk clerk;

    public Product(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            clerk.addProductNum();
        }
    }
}


class Custom implements Runnable{

   private Clerk clerk;

   public Custom(Clerk clerk){
       this.clerk = clerk;
   }

    @Override
    public void run() {

       while (true){

           clerk.reduceProductNum();

       }

    }
}


