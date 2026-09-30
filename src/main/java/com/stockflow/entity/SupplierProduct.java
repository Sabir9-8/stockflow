package com.stockflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "supplier_product")
public class SupplierProduct {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Integer getId() {
        return id;
    }

    public SupplierProduct(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SupplierProduct() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
