package xyz.devyang.sw.core;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import xyz.devyang.sw.storage.DBProperty;
import xyz.devyang.sw.storage.Serialization;

import java.util.ArrayList;
import java.util.Date;

/**
 * Load data into memory from MongoDB
 *
 * Created by YangYu on 11/2/15.
 */
public class DataLoader {

    private MongoClient client;
    private MongoDatabase database;
    private String serializedObject;

    // whether data loading is finished
    private boolean isFinished = false;

    public DataLoader() {
        client = new MongoClient();
        database = client.getDatabase(DBProperty.DBNAME);
    }

    public DataLoader(String serializedObject) {
        this.serializedObject = serializedObject;
    }

    public void loadFromSerialization() {
        Graph graph = (Graph) Serialization.readObject(serializedObject);
        RuntimeCache.GRAPH = graph;
        System.out.println("Finish loading data at " + new Date()
                + "\nNodes: " + RuntimeCache.GRAPH.getNodes().size()
                + "\nEdges: " + RuntimeCache.GRAPH.getEdges().size()
                + "\n*********************************************");
    }

    public void load() {
        MongoCollection collection = database.getCollection(DBProperty.COLL_USER);
        // MongoDB api iterator for iterating the finding result
        FindIterable iterable = collection.find();
        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                // record field: source
                int source = document.get("source", Integer.class);
                // record field: friends
                ArrayList<Integer> friends = document.get("friends", ArrayList.class);

                Node node = new Node(source);
                // put every node into an object pool to avoid duplicated creation
                RuntimeCache.GRAPH.addNode(node);
                for (int i : friends) {
                    RuntimeCache.GRAPH.addEdge(new Edge(node, new Node(i)));
                }
            }
        });
        // Make data loading as finished
        this.isFinished = true;
        System.out.println("Finish loading data at " + new Date()
                + "\nNodes: " + RuntimeCache.GRAPH.getNodes().size()
                + "\nEdges: " + RuntimeCache.GRAPH.getEdges().size()
                + "\n*********************************************");
    }

}
