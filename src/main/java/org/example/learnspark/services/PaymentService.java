package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.example.learnspark.exception.ErrorCreatePaymentException;
import org.example.learnspark.model.CreatePaymentDTO;
import org.example.learnspark.model.mapper.PaymentMapper;

public class PaymentService {
    private PaymentService () { }

    public static Payment createPayment(CreatePaymentDTO createPaymentDTO) {
        Payment payment = PaymentMapper.INSTANCE.CreatePaymentDTOToPayment(createPaymentDTO);
        Payment createdPayment = null;
        try {
            createdPayment = payment.save();
            if(createdPayment.getStatus() == null) {
                throw new ErrorCreatePaymentException(createdPayment.getLastApiResponse().getStringResponse(), createPaymentDTO);
            }
        } catch (MPException e) {
            e.printStackTrace();
        }
        return createdPayment;
    }
}
