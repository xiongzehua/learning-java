package com.xiongzehua.learning.java.concurrency;

/**
 * 经典互斥问题-抢购
 * Created by xiongzehua on 2019/3/13.
 */
public class SaleProblme {
    // 共享资源 商品库存
    public static int itemStock = 100;
    public static void main(String[] args) {
        SaleTask saleTask = new SaleTask();
        // 10个线程负责抢购服务
        for (int i = 0; i < 10; i++) {
            new Thread(saleTask).start();
        }
    }
}

class SaleTask implements Runnable {

    @Override
    public void run() {
        while (true) {
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
        }
    }
}
