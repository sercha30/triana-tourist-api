package com.salesianostriana.dam.TrianaTourist.poi.service;

import com.salesianostriana.dam.TrianaTourist.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoiService {

    private PoiRepository repository;

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

    public Poi edit(UUID id) {
        Poi poi = findById(id);

        return repository.save(poi);
    }

    public void delete(UUID id) {
        Poi poi = findById(id);

        repository.delete(poi);
    }
}
