package com.salesianostriana.dam.TrianaTourist.poi.repos;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PoiRepository extends JpaRepository<Poi, UUID> {
}
