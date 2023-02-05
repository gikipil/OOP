package org.example;

import java.util.ArrayList;

/**
 * interface for sort.
 */

public interface GrafType<T> {

    /**
     * inherited method.
     * get count.
     */


    int getCount();

    /**
     * inherited method.
     * get  index.
     */

    Integer getIndex(T vert);

    /**
     * inherited method.
     * A method for obtaining the edge weight by indexes.
     *
     * @param from the vertex from which the edge comes.
     * @param to   the vertex to which the edge leads.
     * @return weight.
     */

    Integer getWeightEdgeForIndex(int from, int to);

    /**
     * inherited method.
     * Method for getting a list of vertices.
     *
     * @return list of vertices.
     */

    ArrayList<T> getAllVert();
}
