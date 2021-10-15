package org.example.learnspark.model.mapper;

import com.mercadopago.resources.PaymentMethod;
import org.example.learnspark.model.common.PaymentMethodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    PaymentMethodDTO PaymentMethodToPaymentMethodDTO(PaymentMethod paymentMethod);
}
