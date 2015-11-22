package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.*;

import java.util.*;

/**
 * Created by YangYu on 11/22/15.
 */
public class BreadthFirstSearch {


    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private HashMap<Node, Set<Node>> adjList;   // adjacency list format
    private Node source = null;

    /**
     * Computes the shortest path between the source vertex <tt>s</tt>
     * and every other vertex in the graph <tt>G</tt>.
     *
     * @param G the graph
     */
    public BreadthFirstSearch(Graph G) {
        marked = new boolean[GraphProperty.NODE_SIZE+1];
        distTo = new int[GraphProperty.NODE_SIZE+1];
        edgeTo = new int[GraphProperty.NODE_SIZE+1];
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

    /**
     * Breadth-first search from a single source
     *
     * @param G graph
     * @param s source
     */
    public void execute(Graph G, Node s) {
        this.source = s;
        Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < G.getNodes().size(); v++)
            distTo[v] = INFINITY;
        distTo[s.getId()] = 0;
        marked[s.getId()] = true;
        q.add(s.getId());

        while (!q.isEmpty()) {
            int v = q.poll();
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


    // check optimality conditions for single source
    private boolean check(Graph G, Node s) {

        // check that the distance of s = 0
        if (distTo[s.getId()] != 0) {
            System.out.println("distance of source " + s + " to itself = " + distTo[s.getId()]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.getNodes().size(); v++) {
            Iterator<Node> it = adjList.get(new Node(v)).iterator();
            while (it.hasNext()) {
                int w = it.next().getId();
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("distTo[" + v + "] = " + distTo[v]);
                    System.out.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }

            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] + distTo[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.getNodes().size(); w++) {
            if (!hasPathTo(w) || w == s.getId()) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                System.out.println("shortest path edge " + v + "-" + w);
                System.out.println("distTo[" + v + "] = " + distTo[v]);
                System.out.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    /**
     * Reset to be initiation status
     */
    public void reset() {
        marked = new boolean[marked.length];
        distTo = new int[distTo.length];
        edgeTo = new int[edgeTo.length];
    }

    public final int[] getEdgeTo() {
        return edgeTo;
    }

    public final int[] getDistTo() {
        return distTo;
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
            node = graph.getNodes().get(edgeTo[node.getId()]);
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Computes the shortest path between any one of the source vertices in <tt>sources</tt>
     * and every other vertex in graph <tt>G</tt>.
     *
     * @param G       the graph
     * @param sources the source vertices
     */
    /*
    public BreadthFirstSearch(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.getNodes().size()];
        distTo = new int[G.getNodes().size()];
        edgeTo = new int[G.getNodes().size()];
        for (int v = 0; v < G.getNodes().size(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }

    // breadth-first search from multiple sources
    public void execute(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new LinkedList<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.add(s);
        }
        while (!q.isEmpty()) {
            int v = q.poll();
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
    }
    */



    /**
     * Unit tests the <tt>BreadthFirstPaths</tt> data type.
     */
//    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Graph G = new Graph(in);
//        // StdOut.println(G);
//
//        int s = Integer.parseInt(args[1]);
//        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
//
//        for (int v = 0; v < G.V(); v++) {
//            if (bfs.hasPathTo(v)) {
//                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
//                for (int x : bfs.pathTo(v)) {
//                    if (x == s) StdOut.print(x);
//                    else StdOut.print("-" + x);
//                }
//                StdOut.println();
//            } else {
//                StdOut.printf("%d to %d (-):  not connected\n", s, v);
//            }
//
//        }
//    }
}
