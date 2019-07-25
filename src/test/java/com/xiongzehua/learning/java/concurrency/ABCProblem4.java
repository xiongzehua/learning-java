package com.xiongzehua.learning.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiongzehua on 2019/3/27.
 */
public class ABCProblem4 {
    public static int state = 1;
    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(3);
        threadPoolExecutor.execute(new ABCProblem2.TaskA());
        threadPoolExecutor.execute(new ABCProblem2.TaskB());
        threadPoolExecutor.execute(new ABCProblem2.TaskC());
    }

    static class TaskA implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (state % 3 == 1) {
                    System.out.println(Thread.currentThread().getName() + ": A");
                    state++;
                }
            }
        }
    }

    static class TaskB implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (state % 3 == 2) {
                    System.out.println(Thread.currentThread().getName() + ": A");
                    state++;
                }
            }
        }
    }

    static class TaskC implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (state % 3 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": A");
                    state++;
                }
            }
        }
    }
}
