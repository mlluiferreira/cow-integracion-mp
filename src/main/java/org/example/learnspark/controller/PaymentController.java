package org.example.learnspark.controller;

import org.example.learnspark.model.CreatePaymentDTO;
import org.example.learnspark.services.PaymentService;
import org.example.learnspark.util.Parser;
import spark.Request;
import spark.Response;

public class PaymentController {
    private PaymentController() { }

    public static Object createPayment(Request request, Response response) {
        String body = request.body();
        CreatePaymentDTO createPaymentDTO = Parser.toObj(body, CreatePaymentDTO.class);
        return PaymentService.createPayment(createPaymentDTO);
    }
}
