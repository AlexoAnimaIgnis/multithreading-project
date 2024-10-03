package com.concurrency.demo.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents the shared data buffer
 * - produce
 * - consume
 */
public class DataBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 1;

    public synchronized void produce(int product) throws InterruptedException {
        // wait while the buffer is full
        while (buffer.size() == capacity) {
            System.out.println("Producer is waiting buffer is full");
            wait();
        }

        buffer.add(product);
        System.out.println("Produced: " + product);

        notify();
    }

    public synchronized int consume() throws InterruptedException {
        // wait while the buffer is empty
        while (buffer.isEmpty()) {
            System.out.println("Consumer is waiting, buffer is empty");
            wait();
        }

        int product = buffer.remove();
        System.out.println("Consume: " + product);
        notify();

        return product;
    }
}
