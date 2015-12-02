package xyz.devyang.sw.storage;

import java.io.*;

/**
 * Created by YangYu on 11/23/15.
 */
public class Serialization {

    public static void writeObject(Object object, String filename) {
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            ObjectOutputStream objOut = new ObjectOutputStream(outputStream);
            objOut.writeObject(object);
            objOut.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String filename) {
        Object obj = null;
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            ObjectInputStream objIn = new ObjectInputStream(inputStream);
            obj = objIn.readObject();
            objIn.close();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
