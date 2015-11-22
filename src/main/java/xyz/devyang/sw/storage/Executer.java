package xyz.devyang.sw.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by YangYu on 10/31/15.
 */
public class Executer {

    private DataSourceReader reader;
    private MongoDBWriter writer;

    public Executer(File file) throws FileNotFoundException{
        reader = new DataSourceReader(file);
        writer = new MongoDBWriter();
    }

    public Executer(InputStream inputStream) {
        reader = new DataSourceReader(inputStream);
        writer = new MongoDBWriter();
    }

    public void start() {
//        Thread read = new Thread(new Runnable() {
//            public void run() {
//                reader.startRead();
//            }
//        });
//        Thread write = new Thread(new Runnable() {
//            public void run() {
//                writer.insert();
//            }
//        });
//        // start to read
//        read.start();
//        // start to write
//        write.start();
        reader.startRead();
        writer.insert();
    }

}
