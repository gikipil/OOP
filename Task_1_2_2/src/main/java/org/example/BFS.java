package org.example;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * search class in width.
 */
public class BFS<T> implements Iterator<Tree<T>> {

    /**
     * number of iterations.
     */

    private final int iterationCount;

    /**
     * queue array.
     */

    private final ArrayList<Tree<T>> queue;

    /**
     *constructor.
     *
     * @param elem root structure.
     */

    public BFS(Tree<T> elem) {
        iterationCount = elem.getIterationCount();

        queue = new ArrayList<Tree<T>>();
        queue.add(elem);
    }

    /**
     * checking for the next item in the queue.
     *
     * @return true or false.
     */

    @Override
    public boolean hasNext() {
        return queue.size() > 0;
    }

    /**
     * returns the following element.
     *
     * @return next element.
     */

    @Override
    public Tree<T> next() {
        Tree<T> temp = queue.remove(0);

        if (iterationCount != temp.getIterationCount()) {
            throw new ConcurrentModificationException();
        }

        queue.addAll(temp.getChildren());
        return temp;
    }
}
