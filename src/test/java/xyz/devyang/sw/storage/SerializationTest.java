package xyz.devyang.sw.storage;

import org.junit.Test;
import xyz.devyang.sw.core.Graph;
import xyz.devyang.sw.core.Node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by YangYu on 11/23/15.
 */
public class SerializationTest {

    @Test
    public void testWriteObject() throws Exception {
        Node node = new Node(1);
        Serialization.writeObject(node, "node.dat");
    }

    @Test
    public void testReadObject() throws Exception {
        Graph graph = (Graph) Serialization.readObject("scc/63392-2015-11-30-17-34-43.dat");
//        Assert.assertEquals(63392 , graph.getNodes().size());
//        System.out.println(graph.getEdges().size());

        HashMap degree = (HashMap) Serialization.readObject("scc/degree.dat");
        Iterator result = degree.entrySet().iterator();
        System.out.println("degree => size");
        int sum=0;
        while (result.hasNext()) {
            Map.Entry<Integer, List> entry = (Map.Entry) result.next();
            sum += (entry.getKey()*entry.getValue().size());
        }
        System.out.println(sum / graph.getNodes().size());
    }
}