package com.xiongzehua.learning.java.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * Created by xiongzehua on 2019/3/13.
 */
public class ProducerConsumerProblem {
    public static int itemStock = 0;

    public static void main(String[] args) {
        ProducerTask producerTask = new ProducerTask();
        ConsumerTask consumerTask = new ConsumerTask();
        new Thread(producerTask).start();
        new Thread(consumerTask).start();
    }
}

class ProducerTask implements Runnable{

    @Override
    public void run() {
        while (true) {
            if (ProducerConsumerProblem.itemStock >= 1) {
                System.out.println(Thread.currentThread().getName() + ": "
                        + "库存已满，不能生产了：" + ProducerConsumerProblem.itemStock);
            } else {
                ProducerConsumerProblem.itemStock++;
                System.out.println(Thread.currentThread().getName() + ": "
                        + "生产者生产了一个，目前库存：" + ProducerConsumerProblem.itemStock);
            }
        }
    }
}

class ConsumerTask implements Runnable{
    @Override
    public void run() {
        while (true) {
            if (ProducerConsumerProblem.itemStock <= 1) {
                System.out.println(Thread.currentThread().getName() + ": "
                        + "库存为0，不能消费了：" + ProducerConsumerProblem.itemStock);
            } else {
                ProducerConsumerProblem.itemStock--;
                System.out.println(Thread.currentThread().getName() + ": "
                        + "消费者消费了一个，目前库存：" + ProducerConsumerProblem.itemStock);
            }
        }
    }
}