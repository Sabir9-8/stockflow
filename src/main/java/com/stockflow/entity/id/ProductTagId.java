package com.stockflow.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable 
public class ProductTagId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tag_id")
    private Long tagId;
    
    public ProductTagId() {
    }

    public ProductTagId(Long productId, Long tagId) {
        this.productId = productId;
        this.tagId = tagId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, tagId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductTagId other = (ProductTagId) obj;
        
        return Objects.equals(productId, other.productId)
            &&Objects.equals(tagId, other.tagId);
    }
    
}
