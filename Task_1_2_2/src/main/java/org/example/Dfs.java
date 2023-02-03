package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * search class in length.
 * */

public class Dfs<T> implements Iterator<Tree<T>> {

    /**
     * number of iterations.
     */

    private final int iterationCount;

    /**
     * Stack.
     */

    private final Stack<Tree<T>> stack;


    /**
     * temp.
     */

    Tree<T> temp;

    /**
     *Constructor.
     *
     * @param elem root structure.
     */

    public Dfs(Tree<T> elem) {
        iterationCount = elem.getIterationCount();
        stack = new Stack<>();
        stack.push(elem);
    }

    /**
     * checking for the next item in the queue.
     *
     * @return true or false.
     */

    @Override
    public boolean hasNext() {
        return stack.size() > 0;
    }

    /**
     * returns the following element.
     *
     * @return next element.
     */

    @Override
    public Tree<T> next() {
        temp = stack.pop();

        if (iterationCount != temp.getIterationCount()) {
            throw new ConcurrentModificationException();
        }

        stack.addAll(temp.getChildren());
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
