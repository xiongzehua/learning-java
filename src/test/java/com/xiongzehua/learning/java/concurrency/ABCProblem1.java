package com.xiongzehua.learning.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiongzehua on 2019/3/14.
 */
public class ABCProblem1 {
    public static Object lock = new Object();
    public static int state = 0;
    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(3);
        threadPoolExecutor.execute(new TaskA());
        threadPoolExecutor.execute(new TaskB());
        threadPoolExecutor.execute(new TaskC());
    }


    static class TaskA implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (state % 3 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": A");
                    state++;
                    lock.notifyAll();
                }
            }
        }
    }

    static class TaskB implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (state % 3 != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": B");
                    state++;
                    lock.notifyAll();
                }
            }
        }
    }

    static class TaskC implements Runnable{

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (state % 3 != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": C");
                    state++;
                    lock.notifyAll();
                }
            }
        }
    }

}

