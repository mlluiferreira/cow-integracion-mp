package org.example.learnspark.model.common;

import com.mercadopago.resources.datastructures.paymentmethod.FinancialInstitutions;

import java.util.List;

public class PaymentMethodDTO {
    private String id = null;
    private String name = null;
    private String paymentTypeId = null;
    private String status = null;
    private List<FinancialInstitutions> financialInstitutions = null;

    public PaymentMethodDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FinancialInstitutions> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public void setFinancialInstitutions(List<FinancialInstitutions> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }
}
