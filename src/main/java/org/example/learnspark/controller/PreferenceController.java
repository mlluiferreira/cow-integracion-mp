package org.example.learnspark.controller;

import spark.Request;
import spark.Response;

public class PreferenceController {
    private PreferenceController() { }

    public static Object createPreference(Request request, Response response) {
        return null;
    }

    public static Object getPreferenceById(Request request, Response response) {
        String id = request.params("id");
        return null;
    }

    public static Object payPreferenceById(Request request, Response response) {
        String id = request.params("id");
        return null;
    }
}
