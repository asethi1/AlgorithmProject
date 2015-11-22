package xyz.devyang.sw.storage;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * Created by YangYu on 10/31/15.
 */
public class MongoDBWriter implements Observer {

    private MongoClient client;
    private MongoDatabase database;
    private boolean hasUnwritenData = true;
    private HashMap<Integer, List<Integer>> map;

    public MongoDBWriter() {
        client = new MongoClient();
        database = client.getDatabase(DBProperty.DBNAME);
        map = new HashMap<Integer, List<Integer>>();
    }

    public void insertOne(Map.Entry entry) {
        MongoCollection collection = database.getCollection(DBProperty.COLL_USER);

        List<Integer> friendsList = (List) entry.getValue();
//        friendsList.addAll(;
        collection.insertOne(new Document(
                "source", entry.getKey()).append(
                "friends", friendsList));
    }

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
        System.out.println("Finishing writing.Total: "+j);
    }

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
//        return collection.count(new Document("source", pair[0]).append("friends", new Document("$elemMatch", new Document("eq", pair[1]))))>0;
        collection.findOneAndUpdate(new Document("source", pair[0]), new Document("$push", new Document("friends", pair[1])));
        collection.findOneAndUpdate(new Document("source", pair[1]), new Document("$push", new Document("friends", pair[0])));
        return true;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void update(Observable o, Object arg) {
        this.hasUnwritenData = false;
        System.out.println("Writer get notified");
    }
}
