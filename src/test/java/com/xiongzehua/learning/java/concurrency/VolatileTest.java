package com.xiongzehua.learning.java.concurrency;

/**
 * 共享资源source默认为0
 * 线程一负责把source 改为1
 * 线程二死循环，检测到1则停止循环
 * 但测试结果：线程二永远停不下来
 * Created by xiongzehua on 2019/3/13.
 */
public class VolatileTest {
    // 临界资源，加不加volatile完全不同
    public static int source = 0;
    public static void main(String[] args) {
        Thread thread1 = new Thread(new VolatileTask1());
        Thread thread2 = new Thread(new VolatileTask2());
        thread1.start();
        thread2.start();
    }
}

class VolatileTask1 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VolatileTest.source = 1;
        System.out.println(Thread.currentThread().getName() + ": source改为" + VolatileTest.source);
        System.out.println(Thread.currentThread().getName() + ": over");
    }

}

/**
 * while true 和 if的执行速度非常快
 * 以至于线程二读取的一直是自己线程中的source变量副本
 * 而不是内存中的source变量本体
 *
 * 线程一把source变量改为了1
 * 但线程二缓存中的source变量副本一直是0
 */
class VolatileTask2 implements Runnable{

    @Override
    public void run() {
        while (true) {
            if (VolatileTest.source == 1) {
                System.out.println(Thread.currentThread().getName() + ": 检测到1，over");
                break;
            }
        }
    }
}
