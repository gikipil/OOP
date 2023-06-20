package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * a class that reads and stores basic data from a json file.
 */
public class Data {

    /**
     * free space that is available in the storage.
     */
    static int storageSpace;

    /**
     * array storing bakers' work experience.
     */
    static List<Integer> bakers = new ArrayList<>();

    /**
     * an array that stores free space in the trunk of couriers.
     */
    static List<Integer> couriers = new ArrayList<>();

    /**
     *getter for storage space.
     */
    public static int getStorageSpace() {
        return storageSpace;
    }

    /**
     * getter for array bakers.
     */
    public static List<Integer> getBakers() {
        return bakers;
    }

    /**
     * getter for array couriers.
     */
    public static List<Integer> getCouriers() {
        return couriers;
    }

    /**
     * method for reading the file.
     *
     * @param file name.
     */
    public static void read(String file) {
        try (InputStream input = Data.class.getClassLoader().getResourceAsStream(file);) {
            JSONTokener token = new JSONTokener(input);
            JSONObject json = new JSONObject(token);
            storageSpace = json.getInt("Storage");
            JSONArray b = json.getJSONArray("Bakers");
            for (int i = 0; i < b.length(); i++) {
                bakers.add(b.getInt(i));
            }
            JSONArray c = json.getJSONArray("Couriers");
            for (int i = 0; i < b.length(); i++) {
                couriers.add(b.getInt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
