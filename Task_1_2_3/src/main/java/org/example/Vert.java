package org.example;

/**
 * The structure of the vertex and the length of the incoming edge.
 */


public class Vert<T> {

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

    public Vert(T val, Integer weight) {
        this.val = val;
        this.weight = weight;
    }

    /**
     *toString.
     */

    public String toString() {
        return this.val + " (" + this.weight + ")";
    }
}
