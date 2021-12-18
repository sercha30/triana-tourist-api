package com.salesianostriana.dam.TrianaTourist.category.service;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.NamedEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(UUID id) {
        return repository.findById(id).get();
    }

    public Category edit(Category category) {
        return repository.save(category);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Category findCategoryByName(String categoryName) throws NamedEntityNotFoundException {
        Optional<Category> category = repository.findCategoryByNameEquals(categoryName);

        if (category.isEmpty()) {
            throw new NamedEntityNotFoundException(categoryName, Category.class);
        }

        return category.get();
    }
}
