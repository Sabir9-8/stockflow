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
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity 
@Table(name = "warehouse")
public class Warehouse {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true, length = 30)
    private String code;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "active", nullable = false)
    private boolean active;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime creationTime;

    @UpdateTimestamp 
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updationTime;

    @Version 
    private Integer version;

    @OneToMany(mappedBy = "warehouse")
    private List<StockMovement> stockMovements = new ArrayList<>();

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventories = new ArrayList<>();

    public Warehouse(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.active = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addInventory(Inventory inventory) {
        if (inventory == null || inventories.contains(inventory)) return;
        Warehouse currentWarehouse = inventory.getWarehouse();
        if (currentWarehouse != null && currentWarehouse != this) {
            throw new IllegalStateException("This inventory belongs to another warehouse");
        }
        inventory.setWarehouse(this);
        inventories.add(inventory);
    }

    public void addStockMovement(StockMovement stockMovement) {
        if (stockMovement == null || stockMovements.contains(stockMovement)) return;
        Warehouse currentWarehouse = stockMovement.getWarehouse();
        if (currentWarehouse != null && currentWarehouse != this) {
            throw new IllegalStateException("This stock movement belongs to another warehouse");
        }
        stockMovement.setWarehouse(this);
        stockMovements.add(stockMovement);
    }

}
