package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * baker class representing thread.
 */

public class Baker extends Thread {
    Integer order;
    /**
     * the amount of experience used as a coefficient.
     */
    private int experience;

    /**
     * queue with incoming orders.
     */
    List<Integer> queue;

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
    Baker(int experience, List<Integer> queue, BlockingQueue<Integer> storage) {
        this.experience = experience;
        this.queue = queue;
        this.storage = storage;
    }

    /**
     * redefining the run method to create baker's work streams.
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                synchronized (queue) {
                    try {
                        while (queue.isEmpty()){
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        break;
                    }

                    order = queue.get(0);
                    queue.remove(0);
                }
                System.out.println(order + " has been accepted for execution");
                Thread.sleep((100 / experience));
                storage.put(order);
                System.out.println(order + " is ready and waiting for the courier");
            } catch (InterruptedException e) {
                try {
                    storage.put(order);
                    System.out.println(order + " is ready and waiting for the courier");
                } catch (InterruptedException ex) {
                    break;
                }
                break;
            }
        }
    }
}
