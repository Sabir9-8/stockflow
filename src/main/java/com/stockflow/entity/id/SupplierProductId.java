package com.stockflow.entity.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class SupplierProductId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "product_id")
    private Long productId;
}
