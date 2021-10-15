package org.example.learnspark.controller;

import com.mercadopago.resources.Preference;
import org.example.learnspark.model.preference.CreatePreferenceDTO;
import org.example.learnspark.services.PreferenceService;
import org.example.learnspark.util.Parser;
import spark.Request;
import spark.Response;

public class PreferenceController {
    private PreferenceController() { }

    public static Preference createPreference(Request request, Response response) {
        String body = request.body();
        CreatePreferenceDTO createPreferenceDTO = Parser.toObj(body, CreatePreferenceDTO.class);
        return PreferenceService.createPreference(createPreferenceDTO);
    }

    public static Preference getPreferenceById(Request request, Response response) {
        String id = request.params("id");
        return PreferenceService.getPreferenceById(id);
    }

    public static Object payPreferenceById(Request request, Response response) {
        String id = request.params("id");
        Preference preference = PreferenceService.getPreferenceById(id);
        response.redirect(preference.getInitPoint());
        return null;
    }
}
