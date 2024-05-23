package ru.javawebinar.basejava;

public class MainDeadLock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": trys to capture the monitor of object lock1");
            synchronized (MainDeadLock.lock1) {
                System.out.println(Thread.currentThread().getName() + ": the lock1 object monitor is captured");
                System.out.println(Thread.currentThread().getName() + ": trys to capture the monitor of object lock2");
                synchronized (MainDeadLock.lock2) {
                    System.out.println(Thread.currentThread().getName() + ": the lock2 object monitor is captured");
                }
            }
            System.out.println(Thread.currentThread().getName() + "end");
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": trys to capture the monitor of object lock2");
            synchronized (MainDeadLock.lock2) {
                System.out.println(Thread.currentThread().getName() + ": the lock2 object monitor is captured");
                System.out.println(Thread.currentThread().getName() + ": trys to capture the monitor of object lock1");
                synchronized (MainDeadLock.lock1) {
                    System.out.println(Thread.currentThread().getName() + ": the lock1 object monitor is captured");
                }
            }
            System.out.println(Thread.currentThread().getName() + "end");
        }).start();
    }
}
