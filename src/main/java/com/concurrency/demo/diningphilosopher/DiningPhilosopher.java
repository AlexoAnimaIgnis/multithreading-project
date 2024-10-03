package com.concurrency.demo.diningphilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class DiningPhilosopher {

    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 5;
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        Lock[] forks = new ReentrantLock[NUM_PHILOSOPHERS];

        // initialize locks (forks)
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }

        // Create and start philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % NUM_PHILOSOPHERS];

            if (i == NUM_PHILOSOPHERS - 1) {
                philosophers[i] = new Philosopher(i + 1, rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(i + 1, leftFork, rightFork);
            }

            new Thread(philosophers[i]).start();
        }
    }
}
