package org.example;

/**
 * The structure of the vertex and the length of the incoming edge.
 */


public class Vert<T> {

    /**
     * start.
     */

    T start;

    /**
     * Vertex.
     */

    T val;

    /**
     * weght.
     */

    Integer weight;

    /**
     * Constructor.
     */

    public Vert(T from, T val, Integer weight) {
        this.val = val;
        this.weight = weight;
        this.start = from;
    }

}
