package com.mohamedFarag.store.mappers;

import com.mohamedFarag.store.dtos.ProductDto;
import com.mohamedFarag.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto productToProductDto(Product product);
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "id", ignore = true)
    Product  productDtoToProduct(ProductDto productDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category.id")
    void updateProduct(@MappingTarget Product product, ProductDto productDto);
}
