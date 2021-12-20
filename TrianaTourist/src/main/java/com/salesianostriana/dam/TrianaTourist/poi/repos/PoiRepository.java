package com.salesianostriana.dam.TrianaTourist.poi.repos;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PoiRepository extends JpaRepository<Poi, UUID> {

    @Query("select p from Poi p where p.name = ?1")
    Optional<Poi> findByNameEquals(String poiName);
}
