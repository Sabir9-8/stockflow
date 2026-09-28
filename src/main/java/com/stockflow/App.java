package com.stockflow;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stockflow");
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Stockflow started successfully");

        em.close();
        emf.close();
    }
}
