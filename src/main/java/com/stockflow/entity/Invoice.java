package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter 
@Entity 
@Table(name = "invoice")
public class Invoice {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number", unique = true, nullable = false, updatable = false)
    private String invoiceNumber;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;

    @CreationTimestamp 
    @Column(name = "generated_at", nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Lob
    @Column(name = "document_data")
    private byte[] documentData;
    
    public Invoice(String invoiceNumber, Order order, BigDecimal totalAmount, byte[] documentData) {
        this.invoiceNumber = invoiceNumber;
        this.order = order;
        this.totalAmount = totalAmount;
        this.documentData = documentData;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDocumentData(byte[] documentData) {
        this.documentData = documentData;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

}
