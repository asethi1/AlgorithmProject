package xyz.devyang.sw.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Scanner;

/**
 * Reading raw data from txt file with format {index, index, ...}
 *
 * Created by YangYu on 10/31/15.
 */
public class DataSourceReader extends Observable {

    private Scanner scanner;
    private boolean isFinished = false;
    //
    private int recordSize = 0;

    public DataSourceReader(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    public DataSourceReader(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public int startRead() {
        // Record the actual loaded records number
        int lineNum = 1;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");
            try {
                int[] pair = new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])};

                StaticCache.EADGE_CACHE.add(pair);
                this.recordSize++;
            } catch (NumberFormatException e) {
                System.err.println("Error Line: "+lineNum);
            }
            lineNum++;
        }
        this.isFinished = true;
//        notifyObservers();
        System.out.println("Reading Finished, total: " + recordSize);
        return this.recordSize;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

}
