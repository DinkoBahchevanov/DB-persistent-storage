package com.example.persistancestorage.service;

import com.example.persistancestorage.models.Product;
import com.example.persistancestorage.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void put(String key, String value) {
        productRepository.save(new Product(key, value));
    }

    public String get(String key) {
        return productRepository.getById(key).getValue();
    }

    public boolean contains(String key) {
        return productRepository.findById(key).isPresent();
    }

    public boolean remove(String key) {
        productRepository.deleteById(key);
        return !productRepository.existsById(key);
    }
}
