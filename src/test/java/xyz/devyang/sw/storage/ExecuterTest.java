package xyz.devyang.sw.storage;

import org.junit.Test;

/**
 * Created by YangYu on 11/5/15.
 */
public class ExecuterTest {

    @Test
    public void testStart() throws Exception {

        Executer executer = new Executer(getClass().getResourceAsStream("/dataset/fb-connection-test2"));
        executer.start();

    }
}