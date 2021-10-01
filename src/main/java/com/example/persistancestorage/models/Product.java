package com.example.persistancestorage.models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    private String id;
    @Column
    private Object value;

    public Product(String key, String value) {
        this.id = key;
        this.value = value;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
