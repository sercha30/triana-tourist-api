package com.salesianostriana.dam.TrianaTourist.category.repos;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByNameEquals(String categoryName);

    Optional<List<Category>> findByNameIn(List<String> categoryNames);
}
