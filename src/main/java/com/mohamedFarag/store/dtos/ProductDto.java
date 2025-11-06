package com.mohamedFarag.store.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long id;
    @Size(min = 1, max = 20, message = "name field length has to be between 1 and 20")
    private String name;

    private String description;
    @PositiveOrZero(message = "Price can't be negative")
    private BigDecimal price;
    private Byte categoryId;



}
