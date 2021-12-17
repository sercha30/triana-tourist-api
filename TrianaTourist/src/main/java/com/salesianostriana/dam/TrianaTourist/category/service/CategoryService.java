package com.salesianostriana.dam.TrianaTourist.category.service;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
