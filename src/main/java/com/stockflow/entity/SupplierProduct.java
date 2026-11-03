package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.stockflow.entity.id.SupplierProductId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@Getter
@Entity 
@Table(name = "supplier_product")
public class SupplierProduct {

    @EmbeddedId 
    private SupplierProductId id = new SupplierProductId();
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "supplier_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal supplierPrice;

    @Column(name = "minimum_quantity", nullable = false)
    private Integer minimumQuantity;

    @Column(name = "lead_time_days")
    private Integer leadTimeDays;

    @Column(name = "last_supplied_at")
    private LocalDateTime lastSuppliedAt;

    public SupplierProduct(Supplier supplier, Product product) {
        this.supplier = supplier;
        this.product = product;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        if(supplierPrice.doubleValue() <= 0.0){
            throw new IllegalStateException("Price Should be positive");
        }
        this.supplierPrice = supplierPrice;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        if (minimumQuantity <= 0) {
            throw new IllegalStateException("Quantity Should be positive");
        }
        this.minimumQuantity = minimumQuantity;
    }

    public void setLeadTimeDays(Integer leadTimeDays) {
        if (leadTimeDays <= 0) {
            throw new IllegalStateException("Lead Time Days Should be positive");
        }
        this.leadTimeDays = leadTimeDays;
    }

    public void setLastSuppliedAt(LocalDateTime lastSuppliedAt) {
        this.lastSuppliedAt = lastSuppliedAt;
    }

    void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    void setProduct(Product product) {
        if (product == null) return;
        product.getSupplierProducts().add(this);
        this.product = product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, supplier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SupplierProduct other = (SupplierProduct) obj;
        return Objects.equals(product, other.getProduct()) && Objects.equals(supplier, other.getSupplier());
    }

}
