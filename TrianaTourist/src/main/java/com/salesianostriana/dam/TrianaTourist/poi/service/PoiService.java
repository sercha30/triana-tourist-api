package com.salesianostriana.dam.TrianaTourist.poi.service;

import com.salesianostriana.dam.TrianaTourist.category.repos.CategoryRepository;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.CreatePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.GetSinglePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.PoiDtoConverter;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoiService {

    private final PoiRepository repository;
    private final CategoryRepository categoryRepository;
    private final PoiDtoConverter dtoConverter;


    public List<Poi> findAll() throws ListEntityNotFoundException {
        List<Poi> pois = repository.findAll();

        if(pois.isEmpty()) {
            throw new ListEntityNotFoundException(Poi.class);
        }

        return repository.findAll();
    }

    public Poi findById(UUID id) throws SingleEntityNotFoundException{
        Optional<Poi> poi = repository.findById(id);

        if(poi.isEmpty()) {
            throw new SingleEntityNotFoundException(String.valueOf(id), Poi.class);
        }

        return poi.get();
    }

    public GetSinglePoiDto save(CreatePoiDto newPoi) throws EntityExistsException, MissingRequestValueException {
        if(newPoi == null) {
            throw new MissingRequestValueException("{poi.requestBody.missing}");
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
                                .findCategoryByNameEquals(newPoi.getCategoryName()).get())
                )
                .build();

        if(repository.findAll().contains(poi)) {
            throw new EntityExistsException();
        }

        return dtoConverter.convertPoiToGetSinglePoiDto(poi);
    }

    public Poi edit(UUID id) {
        Poi poi = findById(id);

        return repository.save(poi);
    }

    public void delete(UUID id) {
        Poi poi = findById(id);

        repository.delete(poi);
    }
}
