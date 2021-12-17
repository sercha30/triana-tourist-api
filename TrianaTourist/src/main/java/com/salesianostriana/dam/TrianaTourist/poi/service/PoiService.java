package com.salesianostriana.dam.TrianaTourist.poi.service;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoiService {

    private PoiRepository repository;

    public List<Poi> findAll() {
        return repository.findAll();
    }

    public Poi findById(UUID id) {
        return repository.findById(id).get();
    }

    public Poi edit(Poi poi) {
        return repository.save(poi);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
