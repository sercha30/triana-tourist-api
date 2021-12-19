package com.salesianostriana.dam.TrianaTourist.poi.model.dto;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PoiDtoConverter {

    private final CategoryRepository categoryRepository;

    public Poi convertCreatePoiDtoToPoi(CreatePoiDto createPoiDto, Poi poi) {
        return Poi.builder()
                .id(poi.getId())
                .name(createPoiDto.getName())
                .location(createPoiDto.getLocation())
                .description(createPoiDto
                        .getDescription() == null ? createPoiDto.getDescription() : "Sin descripción")
                .date(createPoiDto.getDate())
                .coverPhoto(createPoiDto.getCoverPhoto())
                .photo2(createPoiDto.getPhoto2() == null ? createPoiDto.getPhoto2() : "Sin foto")
                .photo3(createPoiDto.getPhoto3() == null ? createPoiDto.getPhoto2() : "Sin foto")
                .categories(List.of(
                        categoryRepository
                                .findCategoryByNameEquals(createPoiDto.getCategoryName()).get())
                )
                .build();
    }

    public GetSinglePoiDto convertPoiToGetSinglePoiDto(Poi poi) {
        return GetSinglePoiDto.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .date(poi.getDate())
                .description(poi.getDescription())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
                .categoriesName(
                        poi.getCategories()
                                .stream()
                                .map(Category::getName)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
