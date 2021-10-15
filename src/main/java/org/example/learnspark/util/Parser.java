package org.example.learnspark.util;

import com.google.gson.Gson;

public class Parser {

    private static final Gson gson = new Gson();

    private Parser() { }

    public static <T> T toObj(String str, Class<T> type) {
        return gson.fromJson(str, type);
    }
}
