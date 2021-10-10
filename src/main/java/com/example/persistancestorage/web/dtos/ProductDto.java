package com.example.persistancestorage.web.dtos;

public class ProductDto {

    private String key;
    private Object value;

    public ProductDto(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public ProductDto() {
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
