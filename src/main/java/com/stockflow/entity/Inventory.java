package com.stockflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity 
@Table(name = "inventory")
public class Inventory {

    private Integer availableQuantity;

    private Integer reservedQuantity;

    @Version 
    private Integer version;
}
