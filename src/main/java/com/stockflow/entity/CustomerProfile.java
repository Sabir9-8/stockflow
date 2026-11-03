package com.stockflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "customer_profile")
public class CustomerProfile {

    @Id 
    @Column(name = "customer_id")
    private Long customerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "address", nullable = false)
    private String address;

    private String city;

    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    private static void validateString(String string) {
        if (string == null || string.isBlank()){
            throw new IllegalStateException("Data cannot be empty");
        }
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(String address) {
        validateString(address);
        this.address = address;
    }

    public void setCity(String city) {
        validateString(city);
        this.city = city;
    }

    public void setState(String state) {
        validateString(state);
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        validateString(postalCode);
        this.postalCode = postalCode;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerProfile)) return false;
        CustomerProfile that = (CustomerProfile) o;
        return customerId != null && customerId.equals(that.getCustomerId());
    }

    @Override 
    public int hashCode() {
        return getClass().hashCode();
    }
}
