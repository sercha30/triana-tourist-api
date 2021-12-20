package com.salesianostriana.dam.TrianaTourist.poi.service;

import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.MissingPathVariableException;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.MissingRequestBodyException;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.*;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoiService {

    private final PoiRepository repository;
    private final CategoryRepository categoryRepository;
    private final PoiDtoConverter dtoConverter;


    public List<GetListPoiDto> findAll() throws ListEntityNotFoundException {
        List<Poi> pois = repository.findAll();

        if(pois.isEmpty()) {
            throw new ListEntityNotFoundException(Poi.class);
        }

        return pois.stream()
                .map(dtoConverter::convertPoiToGetListPoiDto)
                .collect(Collectors.toList());
    }

    public GetSinglePoiDto findById(UUID id) throws SingleEntityNotFoundException, MissingPathVariableException {
        if(id == null) {
            throw new MissingPathVariableException("{poi.pathVariable.missing}");
        }

        Optional<Poi> poi = repository.findById(id);

        if(poi.isEmpty()) {
            throw new SingleEntityNotFoundException(String.valueOf(id), Poi.class);
        }

        return dtoConverter.convertPoiToGetSinglePoiDto(poi.get());
    }

    public GetSinglePoiDto save(CreatePoiDto newPoi) throws EntityExistsException, MissingRequestBodyException {
        if(newPoi == null) {
            throw new MissingRequestBodyException("{poi.requestBody.missing}");
        }

        Poi poi = Poi.builder()
                .name(newPoi.getName())
                .location(newPoi.getLocation())
                .description(newPoi.getDescription())
                .date(newPoi.getDate())
                .coverPhoto(newPoi.getCoverPhoto())
                .photo2(newPoi.getPhoto2())
                .photo3(newPoi.getPhoto3())
                .name(newPoi.getName())
                .location(newPoi.getLocation())
                .description(newPoi
                        .getDescription() == null ? newPoi.getDescription() : "Sin descripción")
                .date(newPoi.getDate())
                .coverPhoto(newPoi.getCoverPhoto())
                .photo2(newPoi.getPhoto2() == null ? newPoi.getPhoto2() : "Sin foto")
                .photo3(newPoi.getPhoto3() == null ? newPoi.getPhoto2() : "Sin foto")
                .categories(List.of(
                        categoryRepository
                                .findByNameEquals(newPoi.getCategoryName()).get())
                )
                .build();

        if(repository.findAll().contains(poi)) {
            throw new EntityExistsException("{poi.entity.exists}");
        }

        repository.save(poi);

        return dtoConverter.convertPoiToGetSinglePoiDto(poi);
    }

    public GetSinglePoiDto edit(UUID id, EditPoiDto editPoiDto) throws MissingRequestBodyException {
        if(editPoiDto == null) {
            throw new MissingRequestBodyException("{poi.requestBody.missing}");
        }

        GetSinglePoiDto poiDto = findById(id);

        Poi poi = Poi.builder()
                .id(id)
                .name(editPoiDto.getName())
                .location(editPoiDto.getLocation())
                .description(editPoiDto.getDescription())
                .date(editPoiDto.getDate())
                .coverPhoto(editPoiDto.getCoverPhoto())
                .photo2(editPoiDto.getPhoto2())
                .photo3(editPoiDto.getPhoto3())
                .name(editPoiDto.getName())
                .location(editPoiDto.getLocation())
                .description(editPoiDto
                        .getDescription() == null ? editPoiDto.getDescription() : poiDto.getDescription())
                .date(editPoiDto.getDate())
                .coverPhoto(editPoiDto.getCoverPhoto())
                .photo2(editPoiDto.getPhoto2() == null ? editPoiDto.getPhoto2() : poiDto.getPhoto2())
                .photo3(editPoiDto.getPhoto3() == null ? editPoiDto.getPhoto2() : poiDto.getPhoto3())
                .build();

        return dtoConverter.convertPoiToGetSinglePoiDto(repository.save(poi));
    }

    public void delete(UUID id) {
        GetSinglePoiDto poiDto = findById(id);
        Poi poi = dtoConverter.convertGetSinglePoiDtoToPoi(poiDto);

        repository.delete(poi);
    }
}
