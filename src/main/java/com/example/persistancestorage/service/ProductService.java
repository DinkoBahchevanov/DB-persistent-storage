package com.example.persistancestorage.service;

import com.example.persistancestorage.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private SessionFactory sessionFactory;

    public ProductService() {
        Configuration cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public void put(String key, String value) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product product = new Product(key, value);
        session.save(product);

        session.getTransaction().commit();
        session.close();
    }

    public String get(String key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product productToReturn = session.get(Product.class, key);

        session.getTransaction().commit();
        session.close();

        return (String) productToReturn.getValue();
    }

    public String contains(String key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product product = session.get(Product.class, key);

        session.getTransaction().commit();
        session.close();

        return "DB contains object with key " + key + ": " + !(product == null);
    }

    public String remove(String key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product product = session.get(Product.class, key);

        if (product != null) {
            session.delete(product);

            session.getTransaction().commit();
            session.close();

            return String.format("Object with key %s was deleted from DB", key);
        }

        session.getTransaction().commit();
        session.close();
        return String.format("Object with key %s was NOT deleted from DB", key);
    }
}
