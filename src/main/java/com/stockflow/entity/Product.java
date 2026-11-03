package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;

@Getter
@Entity 
@Table(name = "product")
public class Product {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private  String sku;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    private boolean active;

    @Lob 
    @Column(name = "image_data")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updationTime;

    @Version 
    private Integer version;

    @ManyToMany
    @JoinTable(name = "product_tag", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<Tag>();

    @OneToMany(mappedBy = "product")
    private List<SupplierProduct> supplierProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<StockMovement> stockMovements = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories = new ArrayList<>();

    public Product() {
    }

    public Product(String sku, String name, String description, BigDecimal price, Category category) {
        validateSku(sku);
        validateName(name);
        validatePrice(price);
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = true;
        this.category = category;
    }

    public Product(String sku, String name, String description, BigDecimal price, byte[] image, Category category) {
        validateSku(sku);
        validateName(name);
        validatePrice(price);
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = true;
        this.image = image;
        this.category = category;
    }

    private static void validateSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
    }

    private static void validatePrice(BigDecimal price) {
        if (price == null || price.signum() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }   
    }

    public void setSku(String sku) {
        validateSku(sku);
        this.sku = sku;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        validatePrice(price);
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    void setCategory(Category category) {
        this.category = category;
    }

    public void addTag(Tag tag) {
        if (tag == null || !tags.add(tag)) {
            return;
        }
        tag.getProducts().add(this);
    }

    public void removeTag(Tag tag) {
        if (tag == null || !tags.remove(tag)) {
            return;
        }
        tag.getProducts().remove(this);
    }

}
