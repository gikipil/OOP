package org.example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * tree class.
 */

public class Tree<T> implements Iterable<Tree<T>> {

    /**
     * the value of the element.
     */

    private final T data;

    /**
     * struct main root.
     */

    private Tree<T> root;

    /**
     * parent structure.
     */

    private Tree<T> parent;

    /**
     * array of child structures.
     */

    private final ArrayList<Tree<T>> children;

    /**
     * number of iterations.
     */

    private int iterationCount;

    /**
     * getter number of iterations.
     *
     * @return number of iteration.
     */

    public int getIterationCount() {
        return root.iterationCount;
    }

    /**
     * getter data.
     *
     * @return data value.
     */

    public T getData() {
        return data;
    }

    /**
     * getter child.
     *
     * @return child array.
     */

    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    /**
     *constructor.
     *
     * @param start element.
     */

    public Tree(T start) {
        data = start;
        children = new ArrayList<>();
        iterationCount = 0;
        root = this;
        typeIteration = IteratorTree.DFS;
    }

    /**
     * method of adding a child to an element.
     *
     * @param child the value that will become a child.
     *
     * @return returns the new structure of the added element.
     */

    public Tree<T> addChild(T child) {
        root.iterationCount++;
        Tree<T> childTree = new Tree<>(child);
        childTree.parent = this;
        childTree.root = root;
        childTree.iterationCount = this.iterationCount;
        this.children.add(childTree);
        return childTree;
    }

    /**
     *deletes the structure to which it is applied.
     *
     * @return the element that was deleted.
     */

    public T deleteChild() {
        root.iterationCount++;
        if (parent == null) {
            return null;
        }

        this.parent.children.remove(this);

        for (Tree<T> i : children) {
            i.parent = this.parent;
        }

        parent.children.addAll(children);

        return data;
    }

    /**
     * enumeration of iteration methods.
     */

    public enum IteratorTree { DFS, BFS }

    /**
     * iteration type variable.
     */

    private static IteratorTree typeIteration;

    /**
     * setter iteration type.
     *
     * @param newType iteration type.
     */

    public void setTypeIteration(IteratorTree newType) {
        typeIteration = newType;
    }

    /**
     *iteration method by type.
     *
     * @return iterator.
     */

    @Override
    public Iterator<Tree<T>> iterator() {
        if (typeIteration == IteratorTree.DFS) {
            return new Dfs<>(this);
        } else {
            return new Bfs<>(this);
        }
    }





}
