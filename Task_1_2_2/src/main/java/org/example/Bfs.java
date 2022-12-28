package org.example;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * Search class in width.
 */
public class Bfs<T> implements Iterator<Tree<T>> {

    /**
     * Number of iterations.
     */

    private final int iterationCount;

    /**
     * queue array.
     */

    private final List<Tree<T>> queue;

    /**
     * temp.
     */

    Tree<T> temp;

    /**
     *constructor.
     *
     * @param elem root structure.
     */

    public Bfs(Tree<T> elem) {
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
        temp = queue.remove(0);

        if (iterationCount != temp.getIterationCount()) {
            throw new ConcurrentModificationException();
        }

        queue.addAll(temp.getChildren());
        return temp;
    }

    /**
     * method remove.
     */

    @Override
    public void remove() {
        if (iterationCount != temp.getIterationCount()) {
            throw new ConcurrentModificationException();
        }

        try {
            temp.remove();
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
}
