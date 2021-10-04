package com.example.persistancestorage.models;

public class Product {

    private String id;
    private String value;

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

    public void setValue(String value) {
        this.value = value;
    }
}
