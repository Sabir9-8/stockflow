package com.stockflow.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

@Entity 
@Table(name = "supplier")
public class Supplier {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private Boolean active;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at") 
    private LocalDateTime updatedAt;

    @Version 
    private Integer version;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierProduct> supplierProducts = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String name, String email, String phone, Boolean active) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<SupplierProduct> getSuplierProducts() {
        return supplierProducts;
    }

    public void setSuplierProducts(List<SupplierProduct> suplierProducts) {
        this.supplierProducts = suplierProducts;
    }

    public void addProduct(SupplierProduct product) {
        supplierProducts.add(product);
        product.setSupplier(this);
    }

    public void removeProduct(SupplierProduct product) {
        supplierProducts.remove(product);
        product.setSupplier(null);
    }

}
