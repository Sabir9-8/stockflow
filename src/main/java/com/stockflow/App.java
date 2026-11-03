package com.stockflow;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        Properties db = new Properties();

        try (InputStream in = App.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException("db.properties not found");
            }
            db.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.driver", db.getProperty("db.driver"));
        settings.put("jakarta.persistence.jdbc.url", db.getProperty("db.url"));
        settings.put("jakarta.persistence.jdbc.user", db.getProperty("db.username"));
        settings.put("jakarta.persistence.jdbc.password", db.getProperty("db.password"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("stockflow", settings);
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Stockflow started successfully");

        em.close();
        emf.close();
    }
}
