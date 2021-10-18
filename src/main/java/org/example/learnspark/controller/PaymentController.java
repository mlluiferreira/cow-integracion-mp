package org.example.learnspark.controller;

import com.mercadopago.resources.Payment;
import org.example.learnspark.model.payment.CreatePaymentDTO;
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

    public static Object payPaymentById(Request request, Response response) {
        String id = request.params("id");
        Payment payment = PaymentService.getPaymentById(id);

        if(payment.getPaymentTypeId().name().equalsIgnoreCase("ticket")) {
            response.redirect(payment.getTransactionDetails().getExternalResourceUrl());
        } else if(payment.getPaymentMethodId().equalsIgnoreCase("pix")) {
            return payment.getPointOfInteraction().getTransactionData().getQrCodeBase64();
        }

        return null;
    }
}
