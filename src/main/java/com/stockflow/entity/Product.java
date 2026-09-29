package com.stockflow.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity 
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"sku"}))
public class Product {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String sku;

    private String name;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private Boolean active;

    @Lob 
    @Column(name = "image_data")
    private byte[] image;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updationTime;

    @Version 
    private Integer version;

    @ManyToMany()
    @JoinTable
    private Set<Tag> tags = new HashSet<Tag>();

    public Product() {
    }

    public Product(String sku, String name, String description, BigDecimal price, Category category) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product(String sku, String name, String description, BigDecimal price, Boolean active, byte[] image,
            Category category) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.image = image;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdationTime() {
        return updationTime;
    }

    public void setUpdationTime(LocalDateTime updationTime) {
        this.updationTime = updationTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    
}
