package xyz.devyang.sw.storage;

import org.junit.Test;

import java.util.*;

/**
 * Created by YangYu on 10/31/15.
 */
public class MongoDBWriterTest {

    @Test
    public void testInsertOneEadge() throws Exception {
        MongoDBWriter writer = new MongoDBWriter();
        HashMap<Integer, List> map = new HashMap<Integer, List>();
        map.put(1, Arrays.asList(2,3,4));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            writer.insertOne(entry);
        }
    }

    @Test
    public void testInsertPair() throws Exception {
        MongoDBWriter writer = new MongoDBWriter();
        int[] pair = new int[] {6,1};
//        Assert.assertTrue(writer.insertPair(pair));
    }
}