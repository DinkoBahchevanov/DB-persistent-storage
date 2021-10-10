package com.example.persistancestorage.web.dtos.mappers;

import com.example.persistancestorage.models.Pair;
import com.example.persistancestorage.web.dtos.ProductDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductGetDto(Pair product);
    List<ProductDto> productToProductGetDto(List<Pair> productList);
    Pair productPostDtoToProduct(ProductDto productDto);
}
