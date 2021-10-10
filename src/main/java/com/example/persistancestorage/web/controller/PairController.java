package com.example.persistancestorage.web.controller;

import com.example.persistancestorage.service.ProductService;
import com.example.persistancestorage.web.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pairs")
public class PairController {

    private final ProductService productService;

    @Autowired
    public PairController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable String id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> get() {
        return ResponseEntity.ok(productService.get());
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.put(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
