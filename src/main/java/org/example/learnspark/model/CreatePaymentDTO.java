package org.example.learnspark.model;

import java.math.BigDecimal;

public class CreatePaymentDTO {
    private BigDecimal amount = BigDecimal.ZERO;

    private String description = "";

    private String paymentMethodId = "";

    private PayerDTO payer = new PayerDTO();

    public CreatePaymentDTO() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public void setPayer(PayerDTO payer) {
        this.payer = payer;
    }
}
