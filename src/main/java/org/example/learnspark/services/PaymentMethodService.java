package org.example.learnspark.services;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.PaymentMethod;
import org.example.learnspark.model.common.PaymentMethodDTO;
import org.example.learnspark.model.mapper.PaymentMethodMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodService {

    private static List<PaymentMethodDTO> paymentMethodDTOCache = new ArrayList<>();

    private PaymentMethodService() { }

    public static List<PaymentMethodDTO> getPaymentMethods() {
        try {
            if(!paymentMethodDTOCache.isEmpty()) return paymentMethodDTOCache;
            paymentMethodDTOCache = (List<PaymentMethodDTO>) PaymentMethod.all().resources().stream()
                    .map(paymentMethod -> PaymentMethodMapper.INSTANCE.PaymentMethodToPaymentMethodDTO((PaymentMethod) paymentMethod))
                    .collect(Collectors.toList());
        } catch (MPException e) {
            e.printStackTrace();
        }
        return paymentMethodDTOCache;
    }

    public static List<PaymentMethodDTO> getPaymentMethodsByType(String type) {
        if(paymentMethodDTOCache.isEmpty())
            getPaymentMethods();
        return paymentMethodDTOCache.stream()
                .filter(paymentMethod -> paymentMethod.getPaymentTypeId().equals(type)).collect(Collectors.toList());
    }
}
