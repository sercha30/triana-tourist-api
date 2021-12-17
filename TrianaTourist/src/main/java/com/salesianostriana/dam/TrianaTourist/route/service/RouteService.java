package com.salesianostriana.dam.TrianaTourist.route.service;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import com.salesianostriana.dam.TrianaTourist.poi.repos.PoiRepository;
import com.salesianostriana.dam.TrianaTourist.route.model.Route;
import com.salesianostriana.dam.TrianaTourist.route.repos.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {

    private RouteRepository repository;

    public List<Route> findAll() {
        return repository.findAll();
    }

    public Route findById(UUID id) {
        return repository.findById(id).get();
    }

    public Route edit(Route route) {
        return repository.save(route);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
