package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Read {
    public  static List<Integer> read(String name) {
        try (InputStream file = Read.class.getClassLoader().getResourceAsStream(name)) {
            assert file != null;
            Scanner input = new Scanner(file);
            List<Integer> list = new ArrayList<>();
            while (input.hasNextInt()) {
                list.add(input.nextInt());
            }
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public  static List<Stack<Integer>> read(String name, int thr) {
        try (InputStream file = Read.class.getClassLoader().getResourceAsStream(name)) {
            assert file != null;
            Scanner input = new Scanner(file);
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
            return queue;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
