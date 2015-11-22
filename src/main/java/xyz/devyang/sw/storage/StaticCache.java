package xyz.devyang.sw.storage;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A queue structure to store data from datasource while waiting for fetching
 *
 * Created by YangYu on 10/31/15.
 */
public class StaticCache {

    // store the edge pair read from dataset
    public static ConcurrentLinkedDeque<int[]> EADGE_CACHE = new ConcurrentLinkedDeque<int[]>();

}
