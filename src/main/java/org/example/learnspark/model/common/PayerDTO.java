package org.example.learnspark.model.common;

public class PayerDTO {
    private String email = "";

    private String firstName = "";

    private String lastName = "";

    private String cpf = "";

    private AddressDTO address = new AddressDTO();

    public PayerDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCPF() { return cpf; }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
