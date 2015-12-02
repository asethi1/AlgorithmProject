package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.Edge;
import xyz.devyang.sw.core.GProperty;
import xyz.devyang.sw.core.Graph;
import xyz.devyang.sw.core.Node;

import java.util.*;

/**
 * Created by YangYu on 11/30/15.
 */
public class DepthFirstSearch {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private HashMap<Node, Set<Node>> adjList;   // adjacency list format
    private Node source = null;
    private boolean isFinished = false;
    private Node lastVisited = null;
    private Graph G;

    public DepthFirstSearch(Graph G) {
        this.G = G;
        marked = new boolean[GProperty.MAXID+1];
        distTo = new int[GProperty.MAXID+1];
        edgeTo = new int[GProperty.MAXID+1];
        adjList = new HashMap<Node, Set<Node>>();
        for (Edge edge : G.getEdges()) {
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

    public void execute(Node s) {
        this.source = s;
        Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < G.getNodes().size(); v++)
            distTo[v] = INFINITY;
        distTo[s.getId()] = 0;
        marked[s.getId()] = true;
        q.add(s.getId());

        while (!q.isEmpty()) {
            int v = q.poll();
            lastVisited = G.getNodes().get(v);
            Iterator<Node> it = adjList.get(new Node(v)).iterator();
            while (it.hasNext()) {
                int w = it.next().getId();
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
        this.isFinished = true;
    }



}
