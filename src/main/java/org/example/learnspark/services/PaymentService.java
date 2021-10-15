package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.apache.commons.lang3.StringUtils;
import org.example.learnspark.exception.DataNotFoundException;
import org.example.learnspark.exception.ErrorCreatePaymentException;
import org.example.learnspark.model.payment.CreatePaymentDTO;
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

    public static Payment getPaymentById(String id) {
        Payment payment = null;

        if(StringUtils.isBlank(id)) throw new DataNotFoundException("payment not found", id);

        try {
            payment = Payment.findById(id);
        } catch (MPException e) {
            e.printStackTrace();
        }
        if(payment == null || payment.getId() == null)
            throw new DataNotFoundException(payment.getLastApiResponse().getStringResponse(), id);
        return payment;
    }
}
