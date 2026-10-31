package com.stockflow.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter 
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
public class ProductTagId implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tag_id")
    private Long tagId;

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
