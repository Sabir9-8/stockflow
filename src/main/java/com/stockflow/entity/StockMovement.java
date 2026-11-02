package com.stockflow.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@Entity
@NoArgsConstructor 
@Table(name = "stock_movement")
public class StockMovement {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "movement_type", nullable = false, length = 3)
    private String movementType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "reference_id")
    private Long referenceId;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public StockMovement(Product product, Warehouse warehouse, String movementType, Integer quantity,
            String referenceType, Long referenceId) {
        this.product = product;
        this.warehouse = warehouse;
        this.movementType = movementType;
        this.quantity = quantity;
        this.referenceType = referenceType;
        this.referenceId = referenceId;
    }

    private static boolean isValidMovementType(String type) {
        return "IN".equals(type) || "OUT".equals(type) || "ADJ".equals(type);
    }

    void setProduct(Product product) {
        this.product = product;
    }

    void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setMovementType(String movementType) {
        if (isValidMovementType(movementType)){
            this.movementType = movementType;
        }
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }
    
}
