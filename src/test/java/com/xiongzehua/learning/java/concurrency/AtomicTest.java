package com.xiongzehua.learning.java.concurrency;

/**
 * Created by xiongzehua on 2019/3/13.
 */
public class AtomicTest {
    // volatile不能解决原子性的问题
    public static volatile int source = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new AtomicTask()).start();
        }
    }
}

class AtomicTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AtomicTest.source++;
        System.out.println(Thread.currentThread().getName() + ": " + AtomicTest.source);
    }
}
