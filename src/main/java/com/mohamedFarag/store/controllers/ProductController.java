package com.mohamedFarag.store.controllers;


import com.mohamedFarag.store.dtos.ProductDto;
import com.mohamedFarag.store.entities.Product;
import com.mohamedFarag.store.mappers.ProductMapper;
import com.mohamedFarag.store.repositories.CategoryRepository;
import com.mohamedFarag.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(required = false) Byte categoryId)
    {

        List<Product> products = (categoryId == null) ? productRepository.findAll() : productRepository.findByCategory_Id(categoryId);

        return products
                .stream().
                map(productMapper::productToProductDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id")  Long id)
    {
        var product = productRepository.findById(id).orElse(null);

        if (product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.productToProductDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto, UriComponentsBuilder uriBuilder)
    {
        var product = productMapper.productDtoToProduct(productDto);
        product.setCategory(categoryRepository.getReferenceById(productDto.getCategoryId()));
        productRepository.save(product);
        productDto = productMapper.productToProductDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto>  updateProductById(@PathVariable(name = "id") Long id, @Valid @RequestBody ProductDto productDto)
    {
        var product = productRepository.findById(id).orElse(null);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        productMapper.updateProduct(product, productDto);
        product.setCategory(categoryRepository.getReferenceById(productDto.getCategoryId()));
        productRepository.save(product);
        return ResponseEntity.ok(productMapper.productToProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteProductById(@PathVariable(name = "id") Long id)
    {
        var product = productRepository.findById(id).orElse(null);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }


}


