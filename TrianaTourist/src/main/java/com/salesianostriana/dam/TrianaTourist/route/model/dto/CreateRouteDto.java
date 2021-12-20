package com.salesianostriana.dam.TrianaTourist.route.model.dto;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateRouteDto {

    @Column(unique = true)
    private String name;

    @Builder.Default
    @UniqueElements
    private List<Poi> steps = new ArrayList<>();
}
