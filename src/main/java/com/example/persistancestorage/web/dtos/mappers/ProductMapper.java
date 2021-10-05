package com.example.persistancestorage.web.dtos.mappers;

import com.example.persistancestorage.models.Product;
import com.example.persistancestorage.web.dtos.ProductDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductGetDto(Product product);
    List<ProductDto> productToProductGetDto(List<Product> productList);
    Product productPostDtoToProduct(ProductDto productDto);
}
