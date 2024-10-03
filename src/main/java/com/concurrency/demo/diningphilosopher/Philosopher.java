package com.concurrency.demo.diningphilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private final Lock leftFork;
    private final Lock rightFork;
    private final int id;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException

    {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();

                // Attempt to pick up both forks
                if (leftFork.tryLock()) {
                    try {
                        if (rightFork.tryLock()) {
                            try {
                                // Both forks acquired, now eating
                                eat();
                            } finally {
                                // Release right fork after eating
                                rightFork.unlock();
                            }
                        }
                    } finally {
                        // Release left fork if right fork not acquired or after eating
                        leftFork.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
