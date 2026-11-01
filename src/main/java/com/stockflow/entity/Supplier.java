package com.stockflow.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter 
@Entity 
@Table(name = "supplier")
public class Supplier {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    private String phone;

    @Column(name = "active", nullable = false)
    private boolean active;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false) 
    private LocalDateTime updatedAt;

    @Version 
    private Integer version;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierProduct> supplierProducts = new ArrayList<>();

    public Supplier(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addProduct(SupplierProduct supplierProduct) {
        Objects.requireNonNull(supplierProduct, "SupplierProduct cannot be null");
        if(supplierProduct.getSupplier() != null && supplierProduct.getSupplier() != this) {
            throw new IllegalStateException("SupplierProduct already belongs to another supplier");
        }

        if (!supplierProducts.contains(supplierProduct)) {
            supplierProducts.add(supplierProduct);
        }
        supplierProduct.setSupplier(this);
    }

}
