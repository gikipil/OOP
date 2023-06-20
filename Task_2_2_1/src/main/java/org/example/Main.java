package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        List<Integer> queue = new ArrayList<>();
        BlockingQueue<Integer> storage = new LinkedBlockingDeque<>(Data.getStorageSpace());
        List<Integer> bakers = Data.getBakers();
        List<Integer> courier = Data.getCouriers();
        Baker[] threadBakers = new Baker[bakers.size()];
        Courier[] threadCourier = new Courier[courier.size()];
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }
        while (true) {
            /*
            один рабочий день.
             */
            day(queue, storage, bakers, courier, threadBakers, threadCourier);
            System.out.println("day 1 has passed");

            /*
            второй день.
             */
            day(queue, storage, bakers, courier, threadBakers, threadCourier);
            System.out.println("day 2 has passed");

            /*
            третий день.
             */
            day(queue, storage, bakers, courier, threadBakers, threadCourier);
            System.out.println("day 3 has passed");

            break;
        }
    }

    private static void day(List<Integer> queue, BlockingQueue<Integer> storage, List<Integer> bakers, List<Integer> courier, Baker[] threadBakers, Courier[] threadCourier) throws InterruptedException {
        for (int i = 0; i < bakers.size(); i++) {
            threadBakers[i] = new Baker(bakers.get(i), queue, storage);
            threadBakers[i].start();
        }
        for (int i = 0; i < courier.size(); i++) {
            threadCourier[i] = new Courier(courier.get(i), storage);
            threadCourier[i].start();
        }
        Thread.sleep(2000);

        for (int i = 0; i < bakers.size(); i++) {
            threadBakers[i].interrupt();
        }
        for (int i = 0; i < courier.size(); i++) {
            threadCourier[i].interrupt();
        }
    }


}