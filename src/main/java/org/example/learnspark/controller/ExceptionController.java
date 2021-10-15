package org.example.learnspark.controller;

import org.example.learnspark.model.ErrorInfoDTO;
import org.example.learnspark.util.JsonTransformer;
import spark.Request;
import spark.Response;

public class ExceptionController {

    private static final JsonTransformer jsonTransformer = new JsonTransformer();

    private ExceptionController() { }

    public static ErrorInfoDTO handler(Exception exception, Request request, Response response) {
        ErrorInfoDTO errorInfo = ErrorInfoDTO.create(request, exception);
        response.status(errorInfo.statusCode);
        try {
            response.body(jsonTransformer.render(errorInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorInfo;
    }
}
