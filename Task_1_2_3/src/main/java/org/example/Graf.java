package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


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
        Vert<T> temp = new Vert<>(from, to, weight);
        adjacency.get(vertList.get(from)).add(temp);
    }

    /**
     * Method of deleting a vertex.
     *
     * @param vert the vertex to be deleted.
     */

    public void removeVert(T vert) {
        int ind = vertList.get(vert);
        adjacency.remove(ind);
        vertList.remove(vert);
        for (Map.Entry<T, Integer> entry : vertList.entrySet()) {
            if (entry.getValue() > ind) {
                vertList.replace(entry.getKey(), entry.getValue() - 1);
            }
        }
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
            if (i.val == to && i.start == from) {
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

    public List<T> getEdges(T from) {
        List<T> ans = new ArrayList<>();
        for (Vert<T> i : adjacency.get(vertList.get(from))) {
            ans.add(i.val);
        }
        return ans;
    }

    /**
     * all edges.
     */

    public List<Vert<T>> getAllEdges() {
        return adjacency.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Vertex change method.
     *
     * @param before the vertex that needs to be changed.
     *
     * @param after a new vert.
     */

    public  void setVert(T before, T after) {
        vertList.put(after, vertList.get(before));
        vertList.remove(before);
        for (Vert<T> i : adjacency.get(vertList.get(after))) {
            i.val = after;
        }
    }

    /**
     * A method that returns an adjacency matrix.
     */

    public AdjMatrix<T> adjMatrix() {
        int x = 0;
        int y = 0;
        AdjMatrix<T> ans = new AdjMatrix<>();
        List<T> v = this.getAllVert();
        for (T i : v) {
            ans.data.add(x, new ArrayList<>());
            y = 0;
            for (T j : v) {
                ans.data.get(x).add(y, getWeightEdge(i, j));
                y++;
            }
            x++;
        }
        ans.setVertList(vertList);
        return ans;
    }

    /**
     *A method that returns the incidence matrix.
     */

    public IncMatrix<T> incMatrix() {
        int x = 0;
        IncMatrix<T> ans = new IncMatrix<>();
        ArrayList<T> v = this.getAllVert();
        for (T i : v) {
            ans.data.add(x, new ArrayList<>());
            int y = 0;
            for (Vert<T> j : getAllEdges()) {
                if(j.start == i) {
                    Vert<T> a = new Vert<>(j.start, j.val, j.weight);
                    ans.data.get(x).add(y,a);
                } else {
                    Vert<T> a = new Vert<>(j.start, j.val, null);
                    ans.data.get(x).add(y,a);
                }
                y++;
            }
            x++;
        }
        ans.setVertList(vertList);
        return ans;
    }

    /**
     * A method that returns an adjacency list.
     */

    public AdjList<T> adjList() {
        AdjList<T> ans = new AdjList<>();
        ans.data = this.adjacency;
        ans.setVertList(vertList);
        return ans;
    }

}

