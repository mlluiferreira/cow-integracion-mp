package org.example.learnspark.controller;

import com.mercadopago.resources.IdentificationType;
import org.example.learnspark.services.IdentificationService;
import spark.Request;
import spark.Response;

import java.util.List;

public class IdentificationController {
    private IdentificationController() { }

    public static List<IdentificationType> getIdentificationsTypes(Request request, Response response) {
        return IdentificationService.getIdentificationTypes();
    }

    public static List<IdentificationType> getIdentificationsById(Request request, Response response) {
        String id = request.params("id");
        return IdentificationService.getIdentificationById(id);
    }
}
