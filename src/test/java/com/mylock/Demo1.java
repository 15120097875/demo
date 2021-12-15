package com.mylock;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Demo1 {

    /**
     * synchronize同步代码块
     */
    @Test
    public void test() {
        Integer[] arr = {5};
//        Integer[] arr = {1, 3, 5};
        Arrays.asList(arr).parallelStream().forEach(this::lock);
//        Arrays.asList(arr).parallelStream().forEach(this::lock2);
    }

    public void lock(int count) {
        int num = 0;
        synchronized (this) {
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                    num++;
                    System.out.println("当前线程：" + Thread.currentThread().getName() + " num：" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void lock2(int count) {
        int num = 0;
        synchronized (new Demo1()) {
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                    num++;
                    System.out.println("当前线程：" + Thread.currentThread().getName() + " num：" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
