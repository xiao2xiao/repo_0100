package com.clone.mvn_04.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestMain {
	public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

class Consumer implements Runnable {
    private Logger log = LoggerFactory.getLogger(Consumer.class);
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int i = queue.take();
                log.info("consumer :" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info(e.getMessage().toString());
            }
        }
    }
}

class Producer implements Runnable {
    private Logger log = LoggerFactory.getLogger(Producer.class);
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                log.info("producer :" + i);
                queue.put(i);
            } catch (InterruptedException e) {
                log.info(e.getMessage().toString());
            }
        }
    }
}