package xyz.devyang.sw.algorithm;

import org.junit.Before;
import org.junit.Test;
import xyz.devyang.sw.core.Edge;
import xyz.devyang.sw.core.Graph;
import xyz.devyang.sw.core.Node;

import java.util.List;

/**
 * Created by YangYu on 11/21/15.
 */
public class DijkstraSearchTest {

    private Graph graph;
    private DijkstraSearch dijkstra;

    @Before
    public void setUp() {
        graph = new Graph();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        graph.addNode(n1).addNode(n2).addNode(n3).addNode(n4).addNode(n5);

        Edge e1 = new Edge(n1, n2, 10);
        Edge e2 = new Edge(n1, n4, 5);
        Edge e3 = new Edge(n2, n3, 1);
        Edge e4 = new Edge(n4, n2, 3);
        Edge e5 = new Edge(n4 ,n5, 2);
        Edge e6 = new Edge(n4, n3, 9);
        Edge e7 = new Edge(n5, n3, 6);
        graph.addEdge(e1).addEdge(e2).addEdge(e3).addEdge(e4).addEdge(e5).addEdge(e6).addEdge(e7);

        dijkstra = new DijkstraSearch(graph);
    }

    @Test
    public void testExecute() throws Exception {
        dijkstra.execute(new Node(1));
    }


    @Test
    public void testGetPath() throws Exception {
        dijkstra.execute(new Node(1));
        List<Node> list = dijkstra.getPath(new Node(3));
        for (Node node : list) {
            System.out.print(node.getId() + "->");
        }
    }
}