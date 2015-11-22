package xyz.devyang.sw.storage;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * Write records into mongodb
 *
 * Created by YangYu on 10/31/15.
 */
public class MongoDBWriter {

    private MongoClient client;
    private MongoDatabase database;
    private HashMap<Integer, List<Integer>> map; // storage for adjacency list

    public MongoDBWriter() {
        client = new MongoClient();
        database = client.getDatabase(DBProperty.DBNAME);
        map = new HashMap<Integer, List<Integer>>();
    }

    /**
     * Format the data into adjacency list and insert
     *
     */
    public void insert() {
        int i=0;
        int j=0;
        while (!StaticCache.EADGE_CACHE.isEmpty()) {
            int[] pair = StaticCache.EADGE_CACHE.poll();
            if (pair!=null) {
                int source = pair[0];
                int dest = pair[1];
                if (!map.containsKey(source)) {
                    map.put(source, new ArrayList<Integer>());
                }
                if (!map.containsKey(dest)) {
                    map.put(dest, new ArrayList<Integer>());
                }
                map.get(source).add(dest);
                map.get(dest).add(source);
                i++;
            }
        }
        System.out.println("Finishing transform. Total: " + i + " edges");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Set> entry = (Map.Entry<Integer, Set>) iterator.next();
            insertOne(entry);
            j++;
        }
        System.out.println("Finishing writing. Total: "+j);
    }

    /**
     * Insert one adjacency list into db
     *
     * @param entry map entry {1, [2,3,4,5]}
     */
    public void insertOne(Map.Entry entry) {
        MongoCollection collection = database.getCollection(DBProperty.COLL_USER);

        List<Integer> friendsList = (List) entry.getValue();
        collection.insertOne(new Document(
                "source", entry.getKey()).append(
                "friends", friendsList));
    }

    /**
     * Insert one edge into db
     * Slow for large scala of data
     *
     * @param pair edge pair [1,2]
     *
     * @return
     */
    private boolean insertPair(int[] pair) {
        if (pair.length!=2) {
            return false;
        }
        MongoCollection<Document> collection = database.getCollection(DBProperty.COLL_USER);
        if (collection.count(new Document("source", pair[0])) == 0) {
            collection.insertOne(new Document("source", pair[0]).append(
                    "friends", new ArrayList<Integer>()
            ));
        }
        if (collection.count(new Document("source", pair[1])) == 0) {
            collection.insertOne(new Document("source", pair[1]).append(
                    "friends", new ArrayList<Integer>()
            ));
        }
        collection.findOneAndUpdate(new Document("source", pair[0]), new Document("$push", new Document("friends", pair[1])));
        collection.findOneAndUpdate(new Document("source", pair[1]), new Document("$push", new Document("friends", pair[0])));
        return true;
    }

    public final MongoDatabase getDatabase() {
        return database;
    }
}
