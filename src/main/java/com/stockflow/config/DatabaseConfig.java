package com.stockflow.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseConfig {

    private static  Map<String, Object> settings = new HashMap<>();
    static {
        Properties db = new Properties();

        try (InputStream in = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException("db.properties not found");
            }
            db.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }

        settings.put("jakarta.persistence.jdbc.driver", db.getProperty("db.driver"));
        settings.put("jakarta.persistence.jdbc.url", db.getProperty("db.url"));
        settings.put("jakarta.persistence.jdbc.user", db.getProperty("db.username"));
        settings.put("jakarta.persistence.jdbc.password", db.getProperty("db.password"));
    }
    
    public static Map<String, Object> getSettings() {
        return settings;
    }

}
