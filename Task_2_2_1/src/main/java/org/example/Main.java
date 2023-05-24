package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * a class that combines and runs threads.
 */
public class Main {

    /**
     *the main method.
     */
    public static void ini() throws InterruptedException {
        Data.read("2.json");
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        BlockingQueue<Integer> storage = new LinkedBlockingDeque<>(Data.getStorageSpace());
        BlockingQueue<Integer> delivered = new LinkedBlockingDeque<>();
        List<Integer> bakers = Data.getBakers();
        List<Integer> courier = Data.getCouriers();
        Baker[] threadBakers = new Baker[bakers.size()];
        Courier[] threadCourier = new Courier[courier.size()];
        for (int i = 0;i < bakers.size(); i++) {
            threadBakers[i] = new Baker(bakers.get(i), queue, storage);
            threadBakers[i].start();
        }
        for (int i = 0;i < courier.size(); i++) {
            threadCourier[i] = new Courier(courier.get(i), storage, delivered);
            threadCourier[i].start();
        }
        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }
        while(true) {
            if (delivered.size() == 100 ) {
                break;
            }
        }
    }


}