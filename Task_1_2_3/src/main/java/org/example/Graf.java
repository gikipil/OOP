package org.example;

import org.example.Vert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The structure of the vertex and the length of the incoming edge.
 */


/**
 * Graph class.
 */

public class Graf<T> {

    /**
     * The number of vertices.
     */

    int count = 0;

    /**
     * Hashmap of the vertex and its indexer in the list.
     */

    HashMap<T, Integer> vertList = new HashMap<>();

    /**
     * The adjacency list.
     */

    List<ArrayList<Vert<T>>> adjacency = new ArrayList<ArrayList<Vert<T>>>();

    /**
     * Method of adding a vertex.
     *
     * @param vert the vertex to add.
     */

    public void addVert(T vert) {
        vertList.put(vert, count);
        count++;
        adjacency.add(vertList.get(vert), new ArrayList<>());
    }

    /**
     * The method of adding an edge.
     *
     * @param from The vertex from which the edge comes.
     *
     * @param to The vertex to which the edge leads.
     *
     * @param weight the weight of the vertex.
     */

    public void addEdge(T from, T to, Integer weight) {
        Vert<T> temp = new Vert<>(to, weight);
        adjacency.get(vertList.get(from)).add(temp);
    }

    /**
     * Method of deleting a vertex.
     *
     * @param vert the vertex to be deleted.
     */

    public void removeVert(T vert) {
        vertList.remove(vert);
        for (ArrayList<org.example.Vert<T>> verts : adjacency) {
            verts.removeIf(ex -> ex.val == vert);
        }
    }

    /**
     * The method of removing an edge.
     *
     * @param from the vertex from which the edge comes.
     *
     * @param to the vertex to which the edge leads.
     */

    public void removeEdge(T from, T to) {
        adjacency.get(vertList.get(from)).removeIf(ex -> ex.val == to);
    }

    /**
     * Method for obtaining the edge weight.
     *
     * @param from the vertex from which the edge comes.
     *
     * @param to the vertex to which the edge leads.
     *
     * @return weight.
     */

    public Integer getWeightEdge(T from, T to) {
        for (Vert<T> i : adjacency.get(vertList.get(from))) {
            if (i.val == to) {
                return i.weight;
            }
        }

        return null;
    }

    /**
     * A method for obtaining the edge weight by indexes.
     *
     * @param from the vertex from which the edge comes.
     *
     * @param to the vertex to which the edge leads.
     *
     * @return weight.
     */

    public Integer getWeightEdgeForIndex(int from, int to) {
        T toVal = null;
        for (Map.Entry<T, Integer> entry : vertList.entrySet()) {
            if (Objects.equals(to, entry.getValue())) {
                toVal = entry.getKey();
            }
        }
        for (Vert<T> i : adjacency.get(from)) {
            if (i.val == toVal) {
                return i.weight;
            }
        }

        return null;
    }

    /**
     * The method of changing the edge weight.
     *
     * @param from the vertex from which the edge comes.
     *
     * @param to the vertex to which the edge leads.
     *
     * @param val New weight.
     */

    public void setWeightEdge(T from, T to, Integer val) {
        for (Vert<T> i : adjacency.get(vertList.get(from))) {
            if (i.val == to) {
                i.weight = val;
            }
        }
    }

    /**
     * Method for getting a list of vertices.
     *
     * @return list of vertices.
     */

    public ArrayList<T> getAllVert() {
        return new ArrayList<>(vertList.keySet());
    }

    /**
     * Method for getting a list of vertices adjacent to a given one.
     *
     * @param from the vertex from which the edge comes.
     *
     * @return a list of adjacent vertices.
     */

    public ArrayList<T> getEdges(T from) {
        ArrayList<T> ans = new ArrayList<>();
        for (Vert<T> i : adjacency.get(vertList.get(from))) {
            ans.add(i.val);
        }
        return ans;
    }

    /**
     * Vertex change method.
     *
     * @param before the vertex that needs to be changed.
     *
     * @param after a new vert.
     */

    public  void setVert(T before, T after) {
        vertList.put(after,vertList.get(before));
        vertList.remove(before);
        for (Vert<T> i : adjacency.get(vertList.get(after))) {
            i.val = after;
        }
    }

    /**
     * A method that returns an adjacency matrix.
     */

    public ArrayList<ArrayList<Integer>> adjMatrix() {
        int x = 0;
        int y = 0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<T> v = this.getAllVert();
        for (T i : v) {
            ans.add(x, new ArrayList<>());
            y = 0;
            for (T j: v) {
                ans.get(x).add(y, getWeightEdge(i, j));
                y++;
            }
            x++;
        }
        return ans;
    }

    /**
     *A method that returns the incidence matrix.
     */

    public ArrayList<ArrayList<Integer>> incMatrix() {
        int x = 0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<T> v = this.getAllVert();
        for (T i : v) {
            ans.add(x, new ArrayList<>());
            int y = 0;
            for (T j : v) {
                for (Vert<T> l: adjacency.get(vertList.get(j))) {
                    if (i == l.val || i == j) {
                        ans.get(x).add(y, l.weight);
                        y++;
                    } else {
                        ans.get(x).add(y, null);
                        y++;
                    }
                }
            }
            x++;
        }
        return ans;
    }

    /**
     * A method that returns an adjacency list.
     */

    public ArrayList<ArrayList<T>> adjList() {
        int x = 0;
        int y = 0;
        ArrayList<ArrayList<T>> ans = new ArrayList<>();
        ArrayList<T> v = this.getAllVert();
        for (T i : v) {
            ans.add(x, new ArrayList<>());
            y = 0;
            for (T j: v) {
                if (getWeightEdge(i, j) != null) {
                    ans.get(x).add(y, j);
                    y++;
                }
            }
            x++;
        }
        return ans;
    }

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
     * @param arr array of vertices.
     *
     * @param mark array with attendance marks.
     *
     * @return minimum path value.
     */

    private Integer min(Integer [] arr, int [] mark) {
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
     *
     * @param dist array of vertices.
     *
     * @return next vertex.
     */

    private int nextVert(int [] mark, Integer [] dist) {
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
     *
     * @return An array with minimal paths.
     */

    public Integer [] deikstra(T start) {
        int max = Integer.MAX_VALUE;
        int len = this.count;
        Integer [] dist = new Integer[len];
        int [] mark = new int[len];
        for (int i = 0; i < len; i++) {
            dist[i] = max;
            mark[i] = 0;
        }
        int s = vertList.get(start);
        dist[s] = 0;
        mark[s] = 1;
        while (contains(mark)) {
            int u = nextVert(mark,dist);
            if (u == -1) {
                break;
            }
            mark[u] = 2;
            for (int j = 0; j < len; ++j) {
                Integer tempWeight = this.getWeightEdgeForIndex(u, j);
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
     *
     * @return sorted list.
     */

    public ArrayList<T> sortVert(T st) {
        ArrayList<T> list = getAllVert();
        Integer [] data = deikstra(st);
        int len = this.count;
        for (int i = 0; i < len - 1; i++) {
            for(int j = 0; j < len - i - 1; j++) {
                if(data[j + 1] < data[j]) {
                    int swap = data[j];
                    Collections.swap(list, j, j+1);
                    data[j] = data[j + 1];
                    data[j + 1] = swap;

                }
            }
        }
        return list;
    }



}
