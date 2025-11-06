package com.mohamedFarag.store.repositories;

import com.mohamedFarag.store.entities.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    @EntityGraph(attributePaths = "products")   // loads products with categories
    @Query("select c from Category c")
    List<Category> findAllWithProducts();

    @EntityGraph(attributePaths = "products")
    Optional<Category> findById(Byte id);
}