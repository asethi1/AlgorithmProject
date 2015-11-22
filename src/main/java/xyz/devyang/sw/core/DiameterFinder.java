package xyz.devyang.sw.core;

import java.util.*;

/**
 * Created by YangYu on 11/21/15.
 */
public class DiameterFinder {

    private HashMap<Node, Set<Node>> adjList;
    private int[] distTo;
    private Graph graph;

    public DiameterFinder(Graph graph) {
        adjList = new HashMap<Node, Set<Node>>();
        transform(graph);
        this.graph = graph;
        distTo = new int[GraphProperty.NODE_SIZE+1];
        for (int v = 0; v < graph.getNodes().size(); v++)
            distTo[v] = Integer.MAX_VALUE;
    }

    private void transform(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            Node source = edge.getSource();
            Node dest = edge.getDestination();
            if (!adjList.containsKey(source)) {
                adjList.put(source, new HashSet<Node>());
            }
            if (!adjList.containsKey(dest)) {
                adjList.put(dest, new HashSet<Node>());
            }
            adjList.get(source).add(dest);
            adjList.get(dest).add(source);
        }
    }

    public Node bfs(Node node) {
        Node last = null;
        Queue q = new LinkedList<Node>();
        q.add(node);
        node.setIsVisited(true);
        distTo[node.getId()] = 0;
        while (!q.isEmpty()) {
            Node n = (Node)q.poll();
            for (Node adj : adjList.get(n)) {
                if (!adj.isVisited()) {
                    adj.setIsVisited(true);
                    q.add(adj);
                    distTo[adj.getId()] = distTo[n.getId()] + 1;
                    last = adj;
                }
            }
        }
        int max = 0;
//        for (Node n : graph.getNodes()) {
//            if (distTo[n.getId()]>max && distTo[n.getId()]!=Integer.MAX_VALUE) {
//                last = n;
//                max = distTo[node.getId()];
//            }
//        }
        System.out.println("From "+node.getId()+" to " + last.getId());
        System.out.println("length: "+distTo[last.getId()]);
        return last;
    }

    public int diameter(Node node) {
        Node flast = bfs(node);
        reset();
        Node slast = bfs(flast);
        return distTo[slast.getId()];
    }

    public HashMap<Node, Set<Node>> getAdjList() {
        return adjList;
    }

    private void reset() {
        for (int v = 0; v < distTo.length; v++)
            distTo[v] = Integer.MAX_VALUE;
        for (Node node : graph.getNodes().values()) {
            node.setIsVisited(false);
        }
    }
}
