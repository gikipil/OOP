package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Sort<T> {
    /**
     * A method that checks whether an array contains a value.
     *
     * @param arr the array.
     * @return true or false.
     */

    private boolean contains(int[] arr) {
        for (int element : arr) {
            if (element == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method that finds the minimum incomplete vertex.
     *
     * @param arr  array of vertices.
     * @param mark array with attendance marks.
     * @return minimum path value.
     */

    private Integer min(Integer[] arr, int[] mark) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min && mark[i] == 1) {
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * A method that determines the next vertex.
     *
     * @param mark array with attendance marks.
     * @param dist array of vertices.
     * @return next vertex.
     */

    private int nextVert(int[] mark, Integer[] dist) {
        for (int i = 0; i < dist.length; i++) {
            if (mark[i] == 1 && Objects.equals(dist[i], min(dist, mark))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * A method that finds the minimum path from a given vertex using Dijkstra's algorithm.
     *
     * @param start start vertex.
     * @return An array with minimal paths.
     */

    public Integer[] deikstra(T start, GrafType graf) {
        int max = Integer.MAX_VALUE;
        int len = graf.getCount();
        Integer[] dist = new Integer[len];
        int[] mark = new int[len];
        for (int i = 0; i < len; i++) {
            dist[i] = max;
            mark[i] = 0;
        }
        int s = graf.getIndex(start);
        dist[s] = 0;
        mark[s] = 1;
        while (contains(mark)) {
            int u = nextVert(mark, dist);
            if (u == -1) {
                break;
            }
            mark[u] = 2;
            for (int j = 0; j < len; ++j) {
                Integer tempWeight = graf.getWeightEdgeForIndex(u, j);
                if (tempWeight != null) {
                    if (dist[u] + tempWeight < dist[j]) {
                        dist[j] = dist[u] + tempWeight;
                        mark[j] = 1;
                    }
                }
            }
        }
        return dist;
    }

    /**
     * A method for sorting vertices relative to the minimum path from the specified one.
     *
     * @param st start vertex.
     * @return sorted list.
     */

    public ArrayList<T> sortVert(T st, GrafType graf) {
        ArrayList<T> list = graf.getAllVert();
        Integer[] data = deikstra(st, graf);
        int len = graf.getCount();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (data[j + 1] < data[j]) {
                    int swap = data[j];
                    Collections.swap(list, j, j + 1);
                    data[j] = data[j + 1];
                    data[j + 1] = swap;

                }
            }
        }
        return list;
    }
}
