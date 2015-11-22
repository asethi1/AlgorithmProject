package xyz.devyang.sw.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YangYu on 11/5/15.
 */
public class DataLoaderTest {

    @Test
    public void testLoad() throws Exception {
        DataLoader loader = new DataLoader();
        loader.load();
        System.out.println(RuntimeCache.GRAPH.getNodes().size());
        Assert.assertEquals(29309, RuntimeCache.GRAPH.getNodes().size());
    }
}