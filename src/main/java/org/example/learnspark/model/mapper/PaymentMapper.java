package org.example.learnspark.model.mapper;

import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Address;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.example.learnspark.model.common.AddressDTO;
import org.example.learnspark.model.payment.CreatePaymentDTO;
import org.example.learnspark.model.common.PayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    default Payment CreatePaymentDTOToPayment(CreatePaymentDTO createPaymentDTO) {
        Payment payment = new Payment();
        PayerDTO payer = createPaymentDTO.getPayer();
        AddressDTO address = payer.getAddress();
        payment.setTransactionAmount(createPaymentDTO.getAmount().floatValue())
                .setPaymentMethodId(createPaymentDTO.getPaymentMethodId())
                .setDescription(createPaymentDTO.getDescription())
                .setPayer(
                            new Payer()
                                .setEmail(payer.getEmail())
                                .setFirstName(payer.getFirstName())
                                .setLastName(payer.getLastName())
                                .setIdentification(new Identification().setNumber(payer.getCPF()).setType("CPF"))
                                .setAddress(
                                        new Address()
                                                .setCity(address.getCity())
                                                .setNeighborhood(address.getNeighborhood())
                                                .setStreetName(address.getStreetName())
                                                .setZipCode(address.getZipCode())
                                                .setFederalUnit(address.getState())
                                                .setStreetNumber(address.getStreetNumber())
                                )
                );
        return payment;
    }

}
