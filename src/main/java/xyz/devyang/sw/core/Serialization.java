package xyz.devyang.sw.core;

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
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            ObjectInputStream objIn = new ObjectInputStream(inputStream);
            return objIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
