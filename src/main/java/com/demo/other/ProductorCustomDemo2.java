package com.demo.other;


/**
 * 案例：生产者和消费者
 * 生产者(Productor)将产品交给店员(Clerk),而消费者(Customer)从店员处取走产品，店员一次只能持有
 * 固定数量的产品(比如20)，如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店总有空位放产品
 * 了在通知生产者继续生产，如果店中没有产品了，电汇会告诉消费者等一下，如果店中有产品了再通知消费者来
 * 取走产品。
 * 线程数：生产者、消费者
 * 第二种实现方式
 */
public class ProductorCustomDemo2 {

    public static void main(String[] args) {

        Clerk1 clerk1 = new Clerk1();

        Producter producter = new Producter(clerk1);
        Customer customer = new Customer(clerk1);

        Thread t1 = new Thread(producter,"生产者");
        Thread t2 = new Thread(customer,"消费者");

        t1.start();
        t2.start();

    }

}


class Clerk1{

    private int productNum1 = 0;

    public int getProductNum1() {
        return productNum1;
    }

    public void setProductNum1(int productNum1) {
        this.productNum1 = productNum1;
    }

    public void addProductNum1(){
        productNum1++;
        System.out.println(Thread.currentThread().getName()+"，生产第"+productNum1+"件商品");
    }

    public void reduceProductNum1(){
        System.out.println(Thread.currentThread().getName()+"，消耗第"+productNum1+"件商品");
        productNum1--;
    }
}

class Producter implements Runnable{

    private Clerk1 clerk1;

    public Producter(Clerk1 clerk1){
        this.clerk1 = clerk1;
    }

    @Override
    public void run() {
        synchronized (clerk1){
            while (true){
                if (clerk1.getProductNum1() >= 20){
                    try {
                        clerk1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    while (clerk1.getProductNum1() < 20){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        clerk1.addProductNum1();
                    }
                    clerk1.notify();
                }
            }
        }
    }
}

class Customer implements Runnable{

    private Clerk1 clerk1;

    public Customer(Clerk1 clerk1){
        this.clerk1 = clerk1;
    }


    @Override
    public void run() {
        synchronized (clerk1){
            while (true){
                if (clerk1.getProductNum1() <= 0){
                    try {
                        clerk1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    while (clerk1.getProductNum1() > 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        clerk1.reduceProductNum1();
                    }
                    clerk1.notify();
                }
            }
        }
    }
}
