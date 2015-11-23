package xyz.devyang.sw.core;

import java.io.Serializable;

/**
 * Vertex in graph
 *
 * Created by YangYu on 11/2/15.
 */
public class Node implements Serializable {

    private int id;
    private boolean isVisited = false;

    public Node() {
    }

    public Node(int id) {
        this.id = id;
    }

    public Node(int id, boolean isVisited) {
        this.id = id;
        this.isVisited = isVisited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id == node.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}
