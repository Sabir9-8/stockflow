package com.stockflow.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@Getter
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private  Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    @Column(name = "unit_price", nullable = false,
        precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 12, scale = 2)
    private BigDecimal discount;

    @Column(name = "subtotal", nullable = false,
        precision = 12, scale = 2)
    private BigDecimal subtotal;

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal unitPrice, BigDecimal discount, BigDecimal subtotal) {
        if (quantity == null || quantity < 1) {
            throw new IllegalStateException("Order quantity cannot be non-positive");
        }
        if (unitPrice == null || unitPrice.doubleValue() <= 0.0) {
            throw new IllegalStateException("Price should not be negative");
        }
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.subtotal = subtotal;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    void setProduct(Product product) {
        if (product == null) return;
        product.getOrderItems().add(this);
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void calculateSubtotal() {
        BigDecimal base = unitPrice.multiply(new BigDecimal(quantity));
        this.subtotal = discount != null ? base.subtract(discount) : base;
    }

    @Override 
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || !(o instanceof OrderItem)) return false;
        OrderItem item = (OrderItem) o;

        return Objects.equals(order, item.getOrder()) && Objects.equals(product, item.getProduct());
    }

    @Override 
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
