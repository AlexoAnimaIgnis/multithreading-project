package com.concurrency.demo.producerconsumer;

public class Producer implements Runnable {
    private final DataBuffer dataBuffer;

    public Producer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    @Override
    public void run() {
        int product = 0;
        try {
            while (true) {
                dataBuffer.produce(++product); // produce
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
