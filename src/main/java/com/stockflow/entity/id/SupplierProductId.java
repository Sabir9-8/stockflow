package com.stockflow.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter 
@NoArgsConstructor 
@AllArgsConstructor 
public class SupplierProductId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, productId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SupplierProductId other = (SupplierProductId) obj;
        return Objects.equals(supplierId, other.supplierId)
            && Objects.equals(productId, other.productId);
    }
}
