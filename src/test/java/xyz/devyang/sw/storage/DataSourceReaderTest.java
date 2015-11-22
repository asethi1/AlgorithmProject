package xyz.devyang.sw.storage;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YangYu on 10/31/15.
 */
public class DataSourceReaderTest {

    @Test
    public void testStartRead() throws Exception {
        DataSourceReader reader = new DataSourceReader(getClass().getResourceAsStream("/dataset/fb-connection-test"));
        Assert.assertEquals(817035, reader.startRead());
        Assert.assertTrue(reader.isFinished());
    }
}