package xyz.devyang.sw.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YangYu on 11/23/15.
 */
public class SerializationTest {

    @Test
    public void testWriteObject() throws Exception {
        Node node = new Node(1);
        Serialization.writeObject(node, "./node.dat");
    }

    @Test
    public void testReadObject() throws Exception {
        Node node = (Node) Serialization.readObject("./node.dat");
        Assert.assertEquals(1, node.getId());
    }
}