package xyz.devyang.sw.core;

import xyz.devyang.sw.algorithm.BreadthFirstSearch;
import xyz.devyang.sw.algorithm.DijkstraSearch;

import java.util.List;

/**
 * Created by YangYu on 11/3/15.
 */
public class SearchEngine {

    private DijkstraSearch dijkstra;
    private BreadthFirstSearch bfs;

    public SearchEngine() {
    }

    public void dijkstraSearch() {
        DijkstraSearch dijkstra = new DijkstraSearch(RuntimeCache.GRAPH);

        int length = 0;

        for (Node node : RuntimeCache.GRAPH.getNodes().values()) {
            RuntimeCache.GRAPH.reset();
            dijkstra.reset();
            dijkstra.execute(node);
            for (Node node1 : RuntimeCache.GRAPH.getNodes().values()) {
                if (node1 != node) {
                    HashKey key = new HashKey(node, node1);
                    HashKey key_inverse = new HashKey(node1, node);
                    if (!RuntimeCache.PATH.containsKey(key) && !RuntimeCache.PATH.containsKey(key_inverse)) {
                        RuntimeCache.PATH.put(key, dijkstra.getPath(node1));
                        length += dijkstra.getPath(node1).size() - 1;
                    }
                }
            }
        }
        System.out.println("length: " + length + " counter: " + RuntimeCache.PATH.size());
        System.out.println("degree:" + (double) length / RuntimeCache.PATH.size());
    }

    public void bfsSearch() {
        bfs = new BreadthFirstSearch(RuntimeCache.GRAPH);
        int length = 0;

        for (Node node : RuntimeCache.GRAPH.getNodes().values()) {
            bfs.reset();
            RuntimeCache.GRAPH.reset();
            bfs.execute(RuntimeCache.GRAPH, node);
            for (Node dest : RuntimeCache.GRAPH.getNodes().values()) {
                List path = bfs.getPathTo(dest);
                HashKey key = new HashKey(node, dest);
                HashKey key_inverse = new HashKey(dest, node);
                if (!RuntimeCache.PATH.containsKey(key) && !RuntimeCache.PATH.containsKey(key_inverse)) {
                    RuntimeCache.PATH.put(key, path);
                    length += path.size() - 1;
                }
            }
        }

        System.out.println("length: " + length + " counter: " + RuntimeCache.PATH.size());
        System.out.println("degree:" + (double) length / RuntimeCache.PATH.size());
    }


}
