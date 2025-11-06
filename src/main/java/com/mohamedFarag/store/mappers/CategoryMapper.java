package com.mohamedFarag.store.mappers;

import com.mohamedFarag.store.dtos.CategoryDto;
import com.mohamedFarag.store.dtos.ProductDto;
import com.mohamedFarag.store.entities.Category;
import com.mohamedFarag.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface CategoryMapper {


    default CategoryDto categoryToCategoryDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setProducts(productsToProductDtos(category.getProducts(), category.getId()));
        return categoryDto;
    }
    default Set<ProductDto> productsToProductDtos(Set<Product> products, Byte categoryId) {
        if (products == null) {
            return null;
        }

        Set<ProductDto> dtos = new HashSet<>();
        for (Product product : products) {
            dtos.add(productToProductDto(product, categoryId));
        }
        return dtos;
    }
    default ProductDto productToProductDto(Product product, Byte categoryId) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        if (categoryId != null) {
            dto.setCategoryId(categoryId);
        } else {
            dto.setCategoryId(null);
        }

        return dto;
    }


    @Mapping(target = "id", ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    void updateCategory(@MappingTarget Category category, CategoryDto categoryDto);
}