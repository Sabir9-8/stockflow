package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
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

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();

    public Order(String orderNumber, Customer customer, LocalDateTime orderDate, OrderStatus status, BigDecimal totalAmount) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
        if (o == null || !(o instanceof Order)) return  false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.getOrderNumber());
    }

    @Override 
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    public void addItem(OrderItem item) {
        Objects.requireNonNull(item, "Order item cannot be null");
        if (item.getOrder() != null && item.getOrder() != this) {
            throw new IllegalStateException("Order item already belongs to another order");
        }

        if (!items.contains(item)) {
            items.add(item);
            item.setOrder(this);
        }
    }

    public void removeItem(OrderItem item) {
        if (item == null || !items.contains(item)) return;
        item.setOrder(null);
        items.remove(item);
    }

}
