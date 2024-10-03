package com.concurrency.demo.producerconsumer;

public class Consumer implements Runnable {
    private final DataBuffer dataBuffer;

    public Consumer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    @Override
    public void run() {
        try{
            while(true) {
                dataBuffer.consume();
                Thread.sleep(1500);
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();;
        }
    }
}
