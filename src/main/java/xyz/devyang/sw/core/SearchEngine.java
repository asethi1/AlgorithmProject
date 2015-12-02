package xyz.devyang.sw.core;

import xyz.devyang.sw.algorithm.BreadthFirstSearch;
import xyz.devyang.sw.algorithm.DijkstraSearch;
import xyz.devyang.sw.algorithm.FloydWarshall;
import xyz.devyang.sw.storage.Serialization;

import java.util.*;

/**
 * All pairs shortest path search
 * <p/>
 * Created by YangYu on 11/3/15.
 */
public class SearchEngine {

    private DijkstraSearch dijkstra;
    private BreadthFirstSearch bfs;
    private FloydWarshall fw;

    public SearchEngine() {
    }

    public void dijkstraSearch() {
        DijkstraSearch dijkstra = new DijkstraSearch(RuntimeCache.GRAPH);

        int length = 0;

        for (Node node : RuntimeCache.GRAPH.getNodes().values()) {
            RuntimeCache.GRAPH.reset();
            dijkstra.reset();
            dijkstra.execute(node);
            for (Node node1 : RuntimeCache.GRAPH.getNodes().values()) {
                if (node1 != node) {
                    HashKey key = new HashKey(node, node1);
                    HashKey key_inverse = new HashKey(node1, node);
                    if (!RuntimeCache.PATH.containsKey(key) && !RuntimeCache.PATH.containsKey(key_inverse)) {
                        RuntimeCache.PATH.put(key, dijkstra.getPath(node1));
                        length += dijkstra.getPath(node1).size() - 1;
                    }
                }
            }
        }
        System.out.println("Serialize to path.dat at " + new Date());
        Serialization.writeObject(RuntimeCache.PATH, "path.dat");
        System.out.println("Length: " + length + " Counter: " + RuntimeCache.PATH.size());
        System.out.println("Degree:" + (double) length / RuntimeCache.PATH.size());
    }

    public void bfsSearch() {
        bfs = new BreadthFirstSearch(RuntimeCache.GRAPH);
        RuntimeCache.GRAPH.reset();
        long length = 0;
        int counter = 0;
        for (Node node : RuntimeCache.GRAPH.getNodes().values()) {
            counter++;
            bfs.reset();
            bfs.execute(node);
            for (Node dest : RuntimeCache.GRAPH.getNodes().values()) {
                if (!dest.isVisited()) {
                    List path = bfs.getPathTo(dest);
                    if (path != null) {
//                        HashKey key = new HashKey(node, dest);
//                        RuntimeCache.PATH.put(key, path);
                        length += path.size() - 1;
                    }
                }
            }
            node.setIsVisited(true);
            if (counter % 100 == 0) {
                System.out.println("Reaching to " + counter + " at " + new Date());
                System.out.println("Current length: "+length);
            }
        }
//        System.out.println("Serialize to path.dat at " + new Date());
//        Serialization.writeObject(RuntimeCache.PATH, "path.dat");
        System.out.println("Length: " + length + " Counter: " + RuntimeCache.PATH.size());
        System.out.println("Separation Degree:" + (double) length / RuntimeCache.PATH.size());
    }

    public void fwSearch() {
        fw = new FloydWarshall(RuntimeCache.GRAPH);
        int length = 0;
        fw.execute();
//        System.out.println("length: " + length + " counter: " + RuntimeCache.PATH.size());
//        System.out.println("degree:" + (double) length / RuntimeCache.PATH.size());
    }

    public void findLongestPath() {
        bfs = new BreadthFirstSearch(RuntimeCache.GRAPH);
        bfs.execute(RuntimeCache.GRAPH.getNodes().get(1));
        System.out.println("Start From: "+1);
        Node node1 = bfs.getLastVisited();
        System.out.println("First farthest: "+node1);
        bfs.reset();
        bfs.execute(node1);
        Node node2 = bfs.getLastVisited();
        System.out.println("Second farthest: "+node2);
        List path = bfs.getPathTo(node2);
        System.out.println("Path degree: "+(path.size()-1));
        System.out.println("Path:\n"+Arrays.toString(path.toArray()));
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
//                addEdge(e5).addEdge(e6).addEdge(e8).
//                addEdge(e10);
//
//        RuntimeCache.GRAPH = graph;
        DataLoader loader = new DataLoader("scc/63392-2015-11-30-17-34-43.dat");
        loader.loadFromSerialization();
        SearchEngine engine = new SearchEngine();
        System.out.println("Test Breadth First Search start at " + new Date());
        engine.bfsSearch();
        System.out.println("Test Breadth First Search stop at " + new Date());
    }

}
