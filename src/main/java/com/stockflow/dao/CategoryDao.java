package com.stockflow.dao;

import java.util.Optional;

import com.stockflow.config.JPAConfig;
import com.stockflow.entity.Category;

import jakarta.persistence.EntityManager;

public class CategoryDao {

    public void save(Category category) {
        if (category == null) return;
        EntityManager em = JPAConfig.getEntityManager();
        em.persist(category);
    }

    public Category update(Category category) {
        if (category == null) return null;
        EntityManager em = JPAConfig.getEntityManager();
        return em.merge(category);
    }

    public Optional<Category> findById(Long id) {
        if (id == null) return null;
        EntityManager em = JPAConfig.getEntityManager();
        return Optional.ofNullable(em.find(Category.class, id));
    }

    public void delete(Category category) {
        if (category == null) return;
        EntityManager em = JPAConfig.getEntityManager();
        em.remove(category);
    }
}
