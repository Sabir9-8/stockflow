package com.stockflow.entity;

import com.stockflow.entity.id.InventoryId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity 
@Table(name = "inventory")
public class Inventory {

    @EmbeddedId 
    private InventoryId id;

    @Version
    private Integer version;
}
