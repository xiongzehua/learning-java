package com.xiongzehua.learning.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiongzehua on 2019/3/13.
 */
public class SaleProblmeWithLock {
    // 共享资源 商品库存
    public static int itemStock = 100;

    // Lock锁要所有线程用一个
    public static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        SaleTaskWithLock saleTask = new SaleTaskWithLock();
        // 10个线程负责抢购服务
        for (int i = 0; i < 10; i++) {
            new Thread(saleTask).start();
        }
    }
}

class SaleTaskWithLock implements Runnable {

    @Override
    public void run() {
        while (true) {
            SaleProblmeWithLock.lock.lock();
            try {
                if (SaleProblme.itemStock > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int userId = (int)(500 * Math.random()); // 模拟500个用户抢购
                    SaleProblme.itemStock--;
                    System.out.println(Thread.currentThread().getName() + ": "
                            + userId + "号用户抢购成功，剩余库存：" + SaleProblme.itemStock);
                } else {
                    break;
                }
            } catch (Exception e) {

            } finally {
                SaleProblmeWithLock.lock.unlock();
            }
        }
    }
}
