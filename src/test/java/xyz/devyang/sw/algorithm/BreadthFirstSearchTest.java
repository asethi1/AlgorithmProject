package xyz.devyang.sw.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import xyz.devyang.sw.core.Edge;
import xyz.devyang.sw.core.Graph;
import xyz.devyang.sw.core.Node;
import xyz.devyang.sw.core.RuntimeCache;

import java.util.Arrays;

/**
 * Created by YangYu on 11/22/15.
 */
public class BreadthFirstSearchTest {

    private Graph graph;
    private BreadthFirstSearch bfs;

    @Before
    public void setUp() {
        graph = new Graph();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        graph.addNode(n1).addNode(n2).addNode(n3).addNode(n4).addNode(n5).addNode(n6);

        Edge e1 = new Edge(n1, n2, 1);
        Edge e2 = new Edge(n2, n3, 1);
        Edge e3 = new Edge(n3, n4, 1);
        Edge e4 = new Edge(n3, n5, 1);
        Edge e5 = new Edge(n4, n6, 1);
        graph.addEdge(e1).addEdge(e2).addEdge(e3).addEdge(e4).addEdge(e5);

        RuntimeCache.GRAPH = graph;

        bfs = new BreadthFirstSearch(graph);
    }

    @Test
    public void testExecute() throws Exception {
        bfs.execute(graph, graph.getNodes().get(1));
        Assert.assertEquals(4, bfs.getDistTo()[graph.getNodes().get(6).getId()]);
    }

    @Test
    public void testGetPathTo() throws Exception {
        bfs.execute(graph, graph.getNodes().get(1));
        String s = Arrays.toString(bfs.getPathTo(graph.getNodes().get(6)).toArray());
        Assert.assertEquals("[1, 2, 3, 4, 6]", s);
    }
}