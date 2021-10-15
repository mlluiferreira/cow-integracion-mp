package org.example.learnspark.controller;

import org.example.learnspark.model.common.PaymentMethodDTO;
import org.example.learnspark.services.PaymentMethodService;
import spark.Request;
import spark.Response;

import java.util.List;

public class PaymentMethodController {

    private PaymentMethodController() { }

    public static List<PaymentMethodDTO> getPaymentMethods(Request request, Response response) {
        return PaymentMethodService.getPaymentMethods();
    }

    public static List<PaymentMethodDTO> getPaymentMethodsByType(Request request, Response response) {
        String paymentType = request.params("type");
        return PaymentMethodService.getPaymentMethodsByType(paymentType);
    }
}
