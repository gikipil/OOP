package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * the courier class representing thread.
 */

public class Courier extends Thread {

    /**
     * free space in the courier's trunk.
     */
    int space;

    /**
     * queue representing pizza storage.
     */
    BlockingQueue<Integer> storage;

    BlockingQueue<Integer> delivered;

    /**
     * constructor of the courier class.
     *
     * @param space free space in the courier's trunk.
     *
     * @param storage queue representing pizza storage.
     */
    Courier(int space, BlockingQueue<Integer> storage, BlockingQueue<Integer> delivered) {
        this.space = space;
        this.storage = storage;
        this.delivered = delivered;
    }

    /**
     * overriding the run method to create courier work flows.
     */
    @Override
    public void run() {
        while (true) {
            try {
                BlockingQueue<Integer> sp = new LinkedBlockingQueue<>(space);
                Integer order = storage.take();
                sp.put(order);
                System.out.println(order + " was given to the courier");
                while (sp.size() < space) {
                    Integer tempOrder = storage.poll();
                    if (null == tempOrder) {
                        break;
                    }
                    sp.put(tempOrder);
                    System.out.println(tempOrder + " was given to the courier");
                }
                while (!sp.isEmpty()) {
                    Thread.sleep(30);
                    Integer tempOrder = sp.take();
                    System.out.println(tempOrder + " was successfully delivered");
                    delivered.put(tempOrder);
                }
                Thread.sleep(300);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
