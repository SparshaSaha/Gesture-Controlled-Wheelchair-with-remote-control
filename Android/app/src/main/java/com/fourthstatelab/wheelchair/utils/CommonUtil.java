package com.fourthstatelab.wheelchair.utils;

import com.google.gson.Gson;

public class CommonUtil {

    private static Gson gson = new Gson();

    public static void runOnNewThread(Runnable runner) {
        new Thread(runner).start();
    }

    public static <T> T toObject(String receivedData, Class<T> type) {
        try {
            return gson.fromJson(receivedData, type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }
}
