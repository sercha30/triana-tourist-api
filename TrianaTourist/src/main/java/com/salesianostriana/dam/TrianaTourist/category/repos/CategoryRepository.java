package com.salesianostriana.dam.TrianaTourist.category.repos;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    /*
    En estos casos las consultas derivadas por el nombre no funcionaban correctamente,
    por lo que he decidido utilizar consultas JPQL, las cuales sí funcionan
     */
    @Query("select c from Category c where c.name = ?1")
    Optional<Category> findByNameEquals(String categoryName);

    @Query("select c from Category c where c.name in ?1")
    Optional<List<Category>> findByNameIn(List<String> categoryNames);
}
