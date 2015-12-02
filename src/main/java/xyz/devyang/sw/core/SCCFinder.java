package xyz.devyang.sw.core;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import xyz.devyang.sw.algorithm.BreadthFirstSearch;
import xyz.devyang.sw.storage.DBProperty;
import xyz.devyang.sw.storage.Serialization;

import java.util.*;

/**
 * Created by YangYu on 11/30/15.
 */
public class SCCFinder {

    private BreadthFirstSearch bfs;
    private Set<Node> nodeSet = new HashSet<Node>();
    private Set<Edge> edgeSet = new HashSet<Edge>();

    private MongoClient client;
    private MongoDatabase database;

    private int degree = 0;

    public SCCFinder() {
        client = new MongoClient();
        database = client.getDatabase(DBProperty.DBNAME);
    }

    public void getSCC() {
        int i=0;
        while (!RuntimeCache.GRAPH.getNodes().isEmpty()) {
            i++;
            int sizeBefore = RuntimeCache.GRAPH.getNodes().size();
            bfs = new BreadthFirstSearch(RuntimeCache.GRAPH);
            Iterator it = RuntimeCache.GRAPH.getNodes().entrySet().iterator();
            Map.Entry<Integer, Node> source = (Map.Entry) it.next();
            bfs.scc(source.getValue());
            int sizeAfter = RuntimeCache.GRAPH.getNodes().size();
//            System.out.println("Remove: "+(sizeBefore-sizeAfter)+" => Left: "+sizeAfter);
//            if (RuntimeCache.GRAPH.getNodes().size()<=339) {
//                // Get the biggest scc
//                break;
//            }
        }
        System.out.println("Total SCC: "+i);
    }

    public void getStatistic(String serializedObject) {
        final Graph graph = (Graph) Serialization.readObject(serializedObject);
        graph.getEdges().clear();
        HashSet set = new HashSet(graph.getNodes().values());
        for (Edge edge : RuntimeCache.GRAPH.getEdges()) {
            if (set.contains(edge.getSource())||set.contains(edge.getDestination())) {
                graph.addEdge(edge);
                degree++;
            } else {
//                System.out.println(edge.getSource() +"->"+edge.getDestination());
            }
        }
        System.out.println((double)degree/graph.getNodes().size());
        System.out.println(graph.getNodes().size() +" "+graph.getEdges().size());
        Serialization.writeObject(graph, serializedObject);
    }

    public void getDegree(String serializedObject) {
        final Graph graph = (Graph) Serialization.readObject(serializedObject);
        final HashMap<Integer, List> degree = new HashMap<>();
        for (final Node node : graph.getNodes().values()) {
            FindIterable<Document> it = database.getCollection(DBProperty.COLL_USER).
                    find(new Document().append("source", node.getId())).limit(1);
            it.forEach(new Block<Document>() {
                @Override
                public void apply(Document document) {
                    ArrayList<Integer> friends = document.get("friends", ArrayList.class);
                    if (!degree.containsKey(friends.size())) {
                        degree.put(friends.size(), new ArrayList());
                    }
                    List list = degree.get(friends.size());
                    list.add(node.getId());
                }
            });
        }
        Serialization.writeObject(degree, "scc/degree.dat");
        Iterator result = degree.entrySet().iterator();
        System.out.println("degree size");
        while (result.hasNext()) {
            Map.Entry entry = (Map.Entry) result.next();
            System.out.println(entry.getKey() + " " + ((List) entry.getValue()).size());
        }
    }

    public static void main(String[] args) {
        new DataLoader().load();
        SCCFinder finder = new SCCFinder();
        System.out.println("Find SCC start at " + new Date());
        finder.getSCC();
//        finder.getDegree("scc/63392-2015-11-30-17-34-43.dat");
//        System.out.println("Unvisited Node: " + RuntimeCache.GRAPH.getNodes().size());
        System.out.println("Find SCC stop at " + new Date());
    }

}
