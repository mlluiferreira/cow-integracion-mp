package org.example.learnspark.controller;

import com.mercadopago.resources.Payment;
import org.example.learnspark.model.CreatePaymentDTO;
import org.example.learnspark.services.PaymentService;
import org.example.learnspark.util.Parser;
import spark.Request;
import spark.Response;

public class PaymentController {
    private PaymentController() { }

    public static Payment createPayment(Request request, Response response) {
        String body = request.body();
        CreatePaymentDTO createPaymentDTO = Parser.toObj(body, CreatePaymentDTO.class);
        return PaymentService.createPayment(createPaymentDTO);
    }

    public static Payment getPaymentById(Request request, Response response) {
        String id = request.params("id");
        return PaymentService.getPaymentById(id);
    }
}
