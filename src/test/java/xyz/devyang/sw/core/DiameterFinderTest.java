package xyz.devyang.sw.core;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by YangYu on 11/21/15.
 */
public class DiameterFinderTest {

    private Graph graph;
    private DiameterFinder diameterFinder;

    @Before
    public void init() {

        DataLoader loader = new DataLoader();
        loader.load();

        graph = RuntimeCache.GRAPH;


//        graph = new Graph();
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

        diameterFinder = new DiameterFinder(graph);

//        Iterator it = diameterFinder.getAdjList().entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Node, Set<Node>> entry = (Map.Entry<Node, Set<Node>>) it.next();
//            System.out.println(entry.getKey().getId()+": "+ Arrays.toString(entry.getValue().toArray()));
//        }
    }

    @Test
    public void testDiameter() throws Exception {
        int a = 0;
        for (Node node: graph.getNodes().values()) {
            if (node.getId() == 1) {
                a=diameterFinder.diameter(node);
                break;
            }
        }
        System.out.println("diameter: "+a);
    }

    @Test
    public void testBFS() throws Exception {
        for (Node node: graph.getNodes().values()) {
            if (node.getId() == 16) {
                System.out.println(diameterFinder.bfs(node).getId());
                break;
            }
        }
    }

}