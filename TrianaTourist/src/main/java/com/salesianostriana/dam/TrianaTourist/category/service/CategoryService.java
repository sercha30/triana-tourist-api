package com.salesianostriana.dam.TrianaTourist.category.service;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.CreateCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.DtoCategoryConverter;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetListCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetSingleCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final DtoCategoryConverter converter;

    public List<GetListCategoryDto> findAll() throws ListEntityNotFoundException {
        List<Category> categories = repository.findAll();

        if(categories.isEmpty()) {
            throw new ListEntityNotFoundException(Category.class);
        }

        return categories.stream()
                .map(converter::convertCategoryToGetListCategoryDto)
                .collect(Collectors.toList());
    }

    public GetSingleCategoryDto findById(UUID id) throws SingleEntityNotFoundException, MissingPathVariableException {
        if(id == null) {
            throw new MissingPathVariableException("{category.pathVariable.missing}");
        }

        Optional<Category> category = repository.findById(id);

        if(category.isEmpty()) {
            throw new SingleEntityNotFoundException(String.valueOf(id), Category.class);
        }

        return converter.convertCategoryToGetSingleCategoryDto(category.get());
    }

    public GetSingleCategoryDto save(CreateCategoryDto newCategory) throws EntityExistsException, MissingRequestBodyException {
        if(newCategory == null) {
            throw new MissingRequestBodyException("{category.requestBody.missing}");
        }

        Category category = Category.builder()
                .name(newCategory.getName())
                .build();

        if(repository.findAll().contains(category)) {
            throw new EntityExistsException("{category.entity.exists}");
        }

        repository.save(category);

        return converter.convertCategoryToGetSingleCategoryDto(category);
    }

    public GetSingleCategoryDto edit(UUID id, CreateCategoryDto createCategoryDto) throws MissingRequestBodyException{
        if(createCategoryDto == null) {
            throw new MissingRequestBodyException("{category.requestBody.missing}");
        }

        GetSingleCategoryDto categoryDto = findById(id);

        Category category = Category.builder()
                .id(id)
                .name(createCategoryDto.getName())
                .pois(categoryDto.getPois())
                .build();

        return converter.convertCategoryToGetSingleCategoryDto(repository.save(category));
    }

    public void delete(UUID id) {
        GetSingleCategoryDto categoryDto = findById(id);
        Category category = converter.convertSingleCategoryDtoToCategory(categoryDto);

        repository.delete(category);
    }

    public Category findCategoryByName(String categoryName) throws NamedEntityNotFoundException {
        Optional<Category> category = repository.findByNameEquals(categoryName);

        if (category.isEmpty()) {
            throw new NamedEntityNotFoundException(categoryName, Category.class);
        }

        return category.get();
    }
}
