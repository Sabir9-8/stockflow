package com.stockflow.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.stockflow.entity.id.InventoryId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@Entity
@NoArgsConstructor 
@Table(name = "inventory")
public class Inventory {

    @EmbeddedId
    private InventoryId id = new InventoryId();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    @Version 
    private Integer version;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Inventory(Warehouse warehouse, Product product, Integer availableQuantity, Integer reservedQuantity) {
        if (warehouse == null || product == null) {
            throw new IllegalArgumentException("Warehouse and product are required");
        }

        if (availableQuantity == null || availableQuantity < 0
            || reservedQuantity == null || reservedQuantity < 0) {
            throw new IllegalArgumentException("Inventory quantities cannot be negative");
        }
        this.warehouse = warehouse;
        this.product = product;
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
    }

    void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    void setProduct(Product product) {
        if (product == null) return;
        product.getInventories().add(this);
        this.product = product;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        if (availableQuantity == null || availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative");
        }
        this.availableQuantity = availableQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        if (reservedQuantity == null || reservedQuantity < 0) {
            throw new IllegalArgumentException("Reserved quantity cannot be negative");
        }
        this.reservedQuantity = reservedQuantity;
    }

    public void setReorderLevel(Integer reorderLevel) {
        if (reorderLevel != null && reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative");
        }
        this.reorderLevel = reorderLevel;
    }

}
