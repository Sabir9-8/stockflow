package com.stockflow.entity;

import jakarta.persistence.Column;

public class InventoryProduct {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "supplier_id")
    private Integer supplierId;
}
