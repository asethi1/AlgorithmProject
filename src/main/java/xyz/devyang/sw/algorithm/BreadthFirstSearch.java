package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.*;
import xyz.devyang.sw.storage.Serialization;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Breadth first search algorithm implementation
 *
 * Created by YangYu on 11/22/15.
 */
public class BreadthFirstSearch {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private HashMap<Node, Set<Node>> adjList;   // adjacency list format
    private Node source = null;
    private boolean isFinished = false;
    private Node lastVisited = null;
    private Graph G;
    private Graph x;            // SCC

    /**
     * Computes the shortest path between the source vertex <tt>s</tt>
     * and every other vertex in the graph <tt>G</tt>.
     *
     * @param G the graph
     */
    public BreadthFirstSearch(Graph G) {
        this.G = G;
        this.x = new Graph();
        marked = new boolean[GProperty.MAXID+1];
        distTo = new int[GProperty.MAXID+1];
        edgeTo = new int[GProperty.MAXID+1];
        adjList = new HashMap<Node, Set<Node>>();
        for (Edge edge : this.G.getEdges()) {
//            x.addEdge(edge);
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

    /**
     * Breadth-first search from a single source
     *
     * @param s source
     */
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

    public void scc(Node s) {
        this.source = s;
        Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < G.getNodes().size(); v++)
            distTo[v] = INFINITY;
        distTo[s.getId()] = 0;
        marked[s.getId()] = true;
        s.setIsVisited(true);
        q.add(s.getId());
        x.addNode(s);

        while (!q.isEmpty()) {
            int v = q.poll();
            lastVisited = G.getNodes().get(v);
            lastVisited.setIsVisited(true);
            G.removeNode(lastVisited);
            Iterator<Node> it = adjList.get(new Node(v)).iterator();
            while (it.hasNext()) {
                Node next = it.next();
                int w = next.getId();
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    x.addNode(next);
                    q.add(w);
                }
            }
        }
        this.isFinished = true;
        System.out.println("Vertices: "+x.getNodes().size()+" Edges: "+x.getEdges().size());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Serialization.writeObject(x, "scc/" + x.getNodes().size() + "-" + format.format(new Date()) + ".dat");
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
     *
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
     * (or sources) and vertex <tt>v</tt>?
     *
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        return distTo[v];
    }

    /**
     * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
     * and <tt>v</tt>, or <tt>null</tt> if no such path.
     *
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    /**
     * Return the path from source to node
     * @param node destination
     * @return list of path
     */
    public final List<Node> getPathTo(Node node) {
        Graph graph = RuntimeCache.GRAPH;
        List path = new LinkedList();
        path.add(node);
        while (node.getId()!=source.getId()) {
            int id = node.getId();
            node = graph.getNodes().get(edgeTo[node.getId()]);
            if (node == null) {
                return null;
            }
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Reset to be initiation status
     */
    public void reset() {
        if (isFinished) {
            Arrays.fill(marked, false);
            Arrays.fill(distTo, 0);
            Arrays.fill(edgeTo, 0);
            this.isFinished = false;
        }
    }

    public final int[] getEdgeTo() {
        return edgeTo;
    }

    public final int[] getDistTo() {
        return distTo;
    }

    public Node getLastVisited() {
        return this.lastVisited;
    }
}
