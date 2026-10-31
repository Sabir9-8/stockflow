package com.stockflow.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
public class InventoryId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        InventoryId other = (InventoryId) obj;
        return Objects.equals(warehouseId, other.warehouseId)
            &&Objects.equals(productId, other.productId);
    }
}
