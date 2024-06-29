package Famacy.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(); // Load settings from hibernate.cfg.xml

            // Load properties from external config file if it exists
            Properties externalProperties = new Properties();
            try (FileInputStream externalConfig = new FileInputStream("config.properties")) {
                externalProperties.load(externalConfig);
                // Override configuration with external properties
                configuration.addProperties(externalProperties);
            } catch (IOException e) {
                System.out.println("No external config.properties file found. Using default hibernate.cfg.xml settings.");
            }

            // Build session factory
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Failed to create sessionFactory object." + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
