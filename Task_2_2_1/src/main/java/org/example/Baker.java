package org.example;

import java.util.concurrent.BlockingQueue;

/**
 * baker class representing thread.
 */

public class Baker extends Thread {
    /**
     * the amount of experience used as a coefficient.
     */
    int experience;

    /**
     * queue with incoming orders.
     */
    BlockingQueue<Integer> queue;

    /**
     * queue representing pizza storage.
     */
    BlockingQueue<Integer> storage;

    /**
     * constructor of the baker class.
     *
     * @param experience baker's work experience.
     *
     * @param queue queue with incoming orders.
     *
     * @param storage queue representing pizza storage.
     */
    Baker(int experience, BlockingQueue<Integer> queue, BlockingQueue<Integer> storage) {
        this.experience = experience;
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * redefining the run method to create baker's work streams.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Integer order = queue.take();
                System.out.println(order + " has been accepted for execution");
                Thread.sleep((100 / experience));
                storage.put(order);
                System.out.println(order + " is ready and waiting for the courier");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
