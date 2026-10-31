package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor 
@Setter 
@Getter 
@Entity
@Table(name = "orders")
public class Order {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true, updatable = false, length = 30)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public enum OrderStatus {
        DRAFT,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

    @OneToOne(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Invoice invoice;

    public Order(String orderNumber, Customer customer, LocalDateTime orderDate, OrderStatus status, BigDecimal totalAmount, Invoice invoice) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.setInvoice(invoice);
    }

    public void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    public void setInvoice(Invoice invoice) {
        if (this.invoice == invoice) return;
        if (this.invoice != null) {
            this.invoice.setOrder(null);
        }
        this.invoice = invoice;
        if(invoice != null) {
            invoice.setOrder(this);
        }
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return  false;
        Order order = (Order) o;
        return orderNumber != null && orderNumber.equals(order.getOrderNumber());
    }

    @Override 
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

}
