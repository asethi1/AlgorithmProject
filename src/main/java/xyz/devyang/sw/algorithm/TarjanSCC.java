package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.*;

import java.util.*;

/**
 * Created by YangYu on 11/30/15.
 */
public class TarjanSCC {

    private boolean[] marked;        // marked[v] = has v been visited?
    private int[] id;                // id[v] = id of strong component containing v
    private int[] low;               // low[v] = low number of v
    private int pre;                 // preorder number counter
    private int count;               // number of strongly-connected components
    private HashMap<Node, Set<Node>> adjList;   // adjacency list format
    private Stack<Integer> stack;
    private Graph G;


    /**
     * Computes the strong components of the digraph <tt>G</tt>.
     * @param G the digraph
     */
    public TarjanSCC(Graph G) {
        this.G = G;
        this.adjList = new HashMap<Node, Set<Node>>();
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
        this.marked = new boolean[G.getNodes().size()+1];
        this.stack = new Stack<Integer>();
        this.id = new int[G.getNodes().size()+1];
        this.low = new int[G.getNodes().size()+1];
        for (int v = 1; v <= G.getNodes().size(); v++) {
            if (!marked[v]) dfs(v);
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        low[v] = pre++;
        int min = low[v];
        stack.push(v);
        Iterator<Node> it = adjList.get(G.getNodes().get(v)).iterator();
        while (it.hasNext()) {
            int w = it.next().getId();
            if (!marked[w]) dfs(w);
            if (low[w] < min) min = low[w];
        }
        if (min < low[v]) {
            low[v] = min;
            return;
        }
        int w;
        do {
            w = stack.pop();
            id[w] = count;
            low[w] = G.getNodes().size();
        } while (w != v);
        count++;
    }


    /**
     * Returns the number of strong components.
     * @return the number of strong components
     */
    public int count() {
        return count;
    }


    /**
     * Are vertices <tt>v</tt> and <tt>w</tt> in the same strong component?
     * @param v one vertex
     * @param w the other vertex
     * @return <tt>true</tt> if vertices <tt>v</tt> and <tt>w</tt> are in the same
     *     strong component, and <tt>false</tt> otherwise
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * Returns the component id of the strong component containing vertex <tt>v</tt>.
     * @param v the vertex
     * @return the component id of the strong component containing vertex <tt>v</tt>
     */
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
//        Graph graph = new Graph();
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//        Node n6 = new Node(6);
//        Node n7 = new Node(7);
//        graph.addNode(n1).addNode(n2).addNode(n3).addNode(n4).
//                addNode(n5).addNode(n6).addNode(n7);
//
//        Edge e1 = new Edge(n1, n2, 1);
//        Edge e2 = new Edge(n1, n3, 1);
//        Edge e3 = new Edge(n2, n3, 1);
//        Edge e4 = new Edge(n2, n4, 1);
//        Edge e5 = new Edge(n2 ,n5, 1);
//        Edge e6 = new Edge(n3, n5, 1);
//        Edge e7 = new Edge(n3, n6, 1);
//        Edge e8 = new Edge(n4, n5, 1);
//        Edge e9 = new Edge(n5, n6, 1);
//        Edge e10 = new Edge(n6, n7, 1);
//        graph.addEdge(e1).addEdge(e2).addEdge(e3).addEdge(e4).
//                addEdge(e5).addEdge(e6).addEdge(e8).addEdge(e10);
        DataLoader loader = new DataLoader();
        loader.load();
        Graph graph = RuntimeCache.GRAPH;
        TarjanSCC scc = new TarjanSCC(graph);
        // number of connected components
        int M = scc.count();
        System.out.println(M + " components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new LinkedList<Integer>();
        }
        for (int v = 1; v <= graph.getNodes().size(); v++) {
            components[scc.id(v)].offer(v);
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
