package com.example.persistancestorage;

import com.example.persistancestorage.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PersistanceStorageApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(PersistanceStorageApplication.class, args);

        ProductService productService = new ProductService();

        productService.put("Baba", "Meca");
        productService.put("Jaba", "Jabokov");

        System.out.println(productService.contains("Jaba"));
        System.out.println(productService.contains("Baba"));

        System.out.println(productService.remove("Jaba"));

        System.out.println(productService.get("Jaba"));
    }
}
