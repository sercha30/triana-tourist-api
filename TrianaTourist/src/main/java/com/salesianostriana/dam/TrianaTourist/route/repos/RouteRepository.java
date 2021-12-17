package com.salesianostriana.dam.TrianaTourist.route.repos;

import com.salesianostriana.dam.TrianaTourist.route.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}
