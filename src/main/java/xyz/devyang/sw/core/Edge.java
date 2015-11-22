package xyz.devyang.sw.core;

/**
 * Created by YangYu on 11/21/15.
 */
public class Edge {

    private Node source;
    private Node destination;
    private int weight;

    /**
     * unweighted edge constructor
     * @param source
     * @param destination
     */
    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 1;
    }

    /**
     * weighted edge constructor
     * @param source
     * @param destination
     * @param weight
     */
    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (source != null ? !source.equals(edge.source) : edge.source != null) return false;
        return !(destination != null ? !destination.equals(edge.destination) : edge.destination != null);

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }
}
