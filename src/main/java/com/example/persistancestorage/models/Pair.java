package com.example.persistancestorage.models;

public class Pair {

    private String key;
    private Object value;

    public Pair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}