package com.mohamedFarag.store.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto
{
    private Byte id;
    @Size(min = 1, max = 50, message = "Category name must be between 1 and 50")
    private String name;
    private Set<ProductDto> products = new HashSet<>();

}
