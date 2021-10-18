package org.example.learnspark.model.preference;

import org.example.learnspark.model.common.PayerDTO;

import java.util.Collection;

public class CreatePreferenceDTO {

    private Boolean binaryMode = false;

    private String expirationDateFrom;

    private String dateOfExpiration;

    private String expirationDateTo;

    private String purpose;

    private Boolean expires;

    private Collection<ItemDTO> items;

    private PayerDTO payer;

    public CreatePreferenceDTO() { }

    public CreatePreferenceDTO(Collection<ItemDTO> items) {
        this.items = items;
    }

    public Boolean getBinaryMode() {
        return binaryMode;
    }

    public void setBinaryMode(Boolean binaryMode) {
        this.binaryMode = binaryMode;
    }

    public String getExpirationDateFrom() {
        return expirationDateFrom;
    }

    public void setExpirationDateFrom(String expirationDateFrom) {
        this.expirationDateFrom = expirationDateFrom;
    }

    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getExpirationDateTo() {
        return expirationDateTo;
    }

    public void setExpirationDateTo(String expirationDateTo) {
        this.expirationDateTo = expirationDateTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Boolean getExpires() {
        return expires;
    }

    public void setExpires(Boolean expires) {
        this.expires = expires;
    }

    public Collection<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<ItemDTO> items) {
        this.items = items;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public void setPayer(PayerDTO payer) {
        this.payer = payer;
    }
}
