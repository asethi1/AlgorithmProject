package xyz.devyang.sw.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Graph object
 *
 * Created by YangYu on 11/21/15.
 */
public class Graph {

    private Map<Integer, Node> nodes;
    private Set<Edge> edges;

    /**
     * default constructor
     * initialize nodes and edges
     */
    public Graph() {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashSet<Edge>();
    }

    public Graph(HashMap<Integer, Node> nodes, Set<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph addNode(Node node) {
        this.nodes.put(node.getId(), node);
        return this;
    }

    public Graph addEdge(Edge edge) {
        this.edges.add(edge);
        return this;
    }

    public Graph removeNode(Node node) {
        this.nodes.remove(node);
        return this;
    }

    public Graph removeEdge(Edge edge) {
        this.edges.remove(edge);
        return this;
    }

    public final Map<Integer, Node> getNodes() {
        return nodes;
    }

    public final Set<Edge> getEdges() {
        return edges;
    }

    /**
     * Set all the node as not visited
     */
    public void reset() {
        for (Node node : nodes.values()) {
            node.setIsVisited(false);
        }
    }
}
