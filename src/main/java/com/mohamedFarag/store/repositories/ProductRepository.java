package com.mohamedFarag.store.repositories;

import com.mohamedFarag.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Byte categoryId);
}