package xyz.devyang.sw.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by YangYu on 11/21/15.
 */
public class SearchEngineTest {

    private SearchEngine engine = new SearchEngine();

    @Before
    public void setUp() {
        //        Graph graph = new Graph();
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//        Node n6 = new Node(6);
//        graph.addNode(n1).addNode(n2).addNode(n3).addNode(n4).addNode(n5).addNode(n6);
//
//        Edge e1 = new Edge(n1, n2, 1);
//        Edge e2 = new Edge(n2, n3, 1);
//        Edge e3 = new Edge(n3, n4, 1);
//        Edge e4 = new Edge(n3, n5, 1);
//        Edge e5 = new Edge(n4 ,n6, 1);
//        Edge e6 = new Edge(n4, n3, 1);
//        Edge e7 = new Edge(n5, n3, 1);
//        graph.addEdge(e1).addEdge(e2).addEdge(e3).addEdge(e4).addEdge(e5);
//
//        RuntimeCache.GRAPH = graph
        DataLoader loader = new DataLoader();
        loader.load();
    }

    @Test
    public void testDijkstraSearch() throws Exception {
        System.out.println("Test DijkstraSearch Search start at " + new Date());
        engine.dijkstraSearch();
        System.out.println("Test DijkstraSearch Search stop at " + new Date());
    }

    @Test
    public void testBfsSearch() throws Exception {
        System.out.println("Test BreadthFirstSearch Search start at " + new Date());
        engine.bfsSearch();
        System.out.println("Test BreadthFirstSearch Search stop at " + new Date());
    }
}