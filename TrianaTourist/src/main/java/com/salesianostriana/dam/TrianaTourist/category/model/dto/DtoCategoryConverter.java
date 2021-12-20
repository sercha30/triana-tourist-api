package com.salesianostriana.dam.TrianaTourist.category.model.dto;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import org.springframework.stereotype.Component;

@Component
public class DtoCategoryConverter {

    public GetSingleCategoryDto convertCategoryToGetSingleCategoryDto(Category category) {
        return GetSingleCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .pois(category.getPois())
                .build();
    }

    public Category convertSingleCategoryDtoToCategory(GetSingleCategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .pois(categoryDto.getPois())
                .build();
    }

    public GetListCategoryDto convertCategoryToGetListCategoryDto(Category category) {
        return GetListCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
