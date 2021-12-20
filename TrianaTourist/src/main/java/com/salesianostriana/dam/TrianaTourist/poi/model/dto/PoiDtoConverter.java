package com.salesianostriana.dam.TrianaTourist.poi.model.dto;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PoiDtoConverter {

    private final CategoryRepository categoryRepository;

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

    public Poi convertGetSinglePoiDtoToPoi(GetSinglePoiDto poiDto) {
        return Poi.builder()
                .id(poiDto.getId())
                .name(poiDto.getName())
                .location(poiDto.getLocation())
                .date(poiDto.getDate())
                .description(poiDto.getDescription())
                .coverPhoto(poiDto.getCoverPhoto())
                .photo2(poiDto.getPhoto2())
                .photo3(poiDto.getPhoto3())
                .categories(categoryRepository.findByNameIn(poiDto.getCategoriesName())
                        .isPresent() ? categoryRepository.findByNameIn(poiDto.getCategoriesName()).get() : new ArrayList<>())
                .build();
    }

    public GetListPoiDto convertPoiToGetListPoiDto(Poi poi) {
        return GetListPoiDto.builder()
                .id(poi.getId())
                .name(poi.getName())
                .coverPhoto(poi.getCoverPhoto())
                .build();
    }
}
