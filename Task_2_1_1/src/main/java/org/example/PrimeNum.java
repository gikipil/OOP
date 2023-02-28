package org.example;

import static java.lang.Math.sqrt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;


/**
 * PrimeNum class.
 */

public class PrimeNum {

    /**
     * the variable of the presence of a prime number.
     */

    boolean check = false;

    /**
     * setter for check.
     */

    void setCheck() {
        this.check = true;
    }

    /**
     * root.
     */

    PrimeNum root = this;

    /**
     * class for thread.
     */

    static class MyThread extends Thread {
        /**
         * stack number.
         */

        Stack<Integer> num;

        PrimeNum root;

        /**
         * Constructor.
         */

        MyThread(Stack<Integer> in, PrimeNum root) {
            this.num = in;
            this.root = root;
        }

        /**
         * reorganization run.
         */

        public void run() {
            while (!num.empty()) {
                boolean temp = isPrime(num.pop());
                if (!temp) {
                    root.setCheck();
                }
            }
        }

    }

    /**
     * Checks whether a number is prime.
     */

    public static boolean  isPrime(int num) {
        for (int i = 2; i <= sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sequential verification.
     */

    public static boolean seqFind(Scanner input) throws IOException {
        if (input == null) {
            return false;
        }

        while (input.hasNextInt()) {

            int num = input.nextInt();

            if (!isPrime(num)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Parallel stream verification.
     */

    public static boolean psFind(Scanner input) {
        if (input == null) {
            return false;
        }

        List<Integer> list = new ArrayList<Integer>();

        while (input.hasNextInt()) {
            list.add(input.nextInt());
        }

        List<Boolean> ans = list.stream().parallel().map(PrimeNum::isPrime)
                .collect(Collectors.toList());

        return ans.contains(false);

    }

    /**
     * Parallel Thread verification.
     */

    public boolean thrFind(Scanner input, int thr) throws InterruptedException {
        if (input == null) {
            return false;
        }

        List<Stack<Integer>> queue = new ArrayList<>(thr);
        for (int i = 0; i < thr; i++) {
            queue.add(new Stack<>());
        }

        int thrQ = 0;

        while (input.hasNextInt()) {
            if (thrQ < thr) {
                queue.get(thrQ).push(input.nextInt());
                thrQ++;
            } else {
                thrQ = 0;
            }
        }

        MyThread[] thread = new MyThread[thr];
        for (int i = 0; i < thr; i++) {
            thread[i] = new MyThread(queue.get(i), root);
            thread[i].start();
        }

        for (int i = 0; i < thr; i++) {
            thread[i].join();
        }

        return this.check;

    }

    /*

    public  void  file(int num) throws IOException {
        BufferedWriter writer = new BufferedWriter(
        new FileWriter("src/test/resources/16.txt", true));
        writer.write(String.valueOf(1));
        for (int i = 2; i < num; i++) {
            if (isPrime(i)) {
                writer.append(" ");
                writer.append(String.valueOf(i));
            }
        }
        writer.close();
    }
     */
}
