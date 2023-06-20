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
    private int space;

    /**
     * queue representing pizza storage.
     */
    BlockingQueue<Integer> storage;

    BlockingQueue<Integer> sp;

    /**
     * constructor of the courier class.
     *
     * @param space free space in the courier's trunk.
     *
     * @param storage queue representing pizza storage.
     */
    Courier(int space, BlockingQueue<Integer> storage) {
        this.space = space;
        this.storage = storage;
        sp = new LinkedBlockingQueue<>(space);
    }

    /**
     * overriding the run method to create courier work flows.
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Integer order = storage.take();
                sp.put(order);
                System.out.println(order + " was given to the courier");
                while (sp.size() < space && !isInterrupted()) {
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
                }
                Thread.sleep(300);

            } catch (InterruptedException e) {
                try {
                    while (!sp.isEmpty()) {
                        Integer tempOrder = null;
                        tempOrder = sp.take();
                        System.out.println(tempOrder + " was successfully delivered");
                    }
                } catch (InterruptedException ex) {
                    break;
                }
                break;
            }
        }
    }
}
