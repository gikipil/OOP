package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * class for adjlist.
 */

public class AdjList<T> implements GrafType<T> {

    /**
     * adjList.
     */

    List<ArrayList<Vert<T>>> data = new ArrayList<>();

    /**
     * getter data.
     */

    public List<ArrayList<Vert<T>>> getData() {
        return data;
    }

    /**
     * map for linking the name to the index.
     */

    HashMap<T, Integer> vertList = new HashMap<>();

    /**
     * setter for map.
     */

    void setVertList(HashMap<T, Integer> list) {
        vertList = list;
    }

    /**
     * inherited method.
     * get count.
     */

    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * inherited method.
     * get  index.
     */

    @Override
    public Integer getIndex(T vert) {
        return vertList.get(vert);
    }

    /**
     * inherited method.
     * A method for obtaining the edge weight by indexes.
     *
     * @param from the vertex from which the edge comes.
     * @param to   the vertex to which the edge leads.
     * @return weight.
     */

    @Override
    public Integer getWeightEdgeForIndex(int from, int to) {
        T toVal = null;
        for (Map.Entry<T, Integer> entry : vertList.entrySet()) {
            if (Objects.equals(to, entry.getValue())) {
                toVal = entry.getKey();
            }
        }
        for (Vert<T> i : data.get(from)) {
            if (i.val == toVal) {
                return i.weight;
            }
        }

        return null;
    }

    /**
     * inherited method.
     * Method for getting a list of vertices.
     *
     * @return list of vertices.
     */

    @Override
    public ArrayList<T> getAllVert() {
        return new ArrayList<>(vertList.keySet());
    }
}
