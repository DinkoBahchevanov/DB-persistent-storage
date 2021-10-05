package com.example.persistancestorage.service;

import com.example.persistancestorage.web.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto put(ProductDto productDto);
    ProductDto get(String key);
    List<ProductDto> get();
    boolean contains(String key);
    boolean remove(String key);
}
