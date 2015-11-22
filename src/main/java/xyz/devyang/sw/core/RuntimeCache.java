package xyz.devyang.sw.core;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YangYu on 11/2/15.
 */
public class RuntimeCache {

    // Graph storage in memory
//    public static HashMap<Node, Node> ADJLIST = new HashMap<Node, Node>();

    // Graph storage in memory
    public static Graph GRAPH = new Graph();

    // Search result
    public static HashMap<HashKey, List> PATH = new HashMap<HashKey, List>();

}
