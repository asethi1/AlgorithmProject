package xyz.devyang.sw.core;

import java.util.*;

/**
 * Class representing vertex
 *
 * Created by YangYu on 11/2/15.
 */
public class Node {

    private int id;
    private boolean isVisited = false;
//    private Set<Node> adjacency;

    public Node() {
    }

    public Node(int id) {
        this.id = id;
//        adjacency = new HashSet<Node>();
    }

    public Node(int id, boolean isVisited, Set<Node> adjacency) {
        this.id = id;
        this.isVisited = isVisited;
//        this.adjacency = adjacency;
    }

    /*
    public void addAdjacentNode(Node node) {
        this.adjacency.add(node);
        node.getAdjacency().add(this);
    }
    */

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

//    public Set<Node> getAdjacency() {
//        return adjacency;
//    }

//    public void setAdjacency(Set<Node> adjacency) {
//        this.adjacency = adjacency;
//    }

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

    /*
    @Override
    public String toString() {
        List<Integer> list = new ArrayList<Integer>();
        for (Node node : adjacency) {
            list.add(node.getId());
        }
        return id+": "+ Arrays.toString(list.toArray());
    }
    */

    @Override
    public String toString() {
        return ""+id;
    }
}
