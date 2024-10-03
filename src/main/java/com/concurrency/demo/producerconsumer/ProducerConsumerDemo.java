package com.concurrency.demo.producerconsumer;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        DataBuffer dataBuffer = new DataBuffer();

        Producer producer = new Producer(dataBuffer);
        Consumer consumer = new Consumer(dataBuffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
