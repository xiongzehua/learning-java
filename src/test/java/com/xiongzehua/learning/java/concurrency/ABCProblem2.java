package com.xiongzehua.learning.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiongzehua on 2019/3/20.
 */
public class ABCProblem2 {
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
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
                try {
                    lock.lock();
                    while (state % 3 != 1) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": A");
                    state++;
                    condition.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class TaskB implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (state % 3 != 2) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": B");
                    state++;
                    condition.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class TaskC implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (state % 3 != 0) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": C");
                    state++;
                    condition.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

