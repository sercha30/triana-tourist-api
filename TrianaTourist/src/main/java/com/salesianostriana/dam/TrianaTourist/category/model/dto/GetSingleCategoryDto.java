package com.salesianostriana.dam.TrianaTourist.category.model.dto;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetSingleCategoryDto {

    private UUID id;
    private String name;

    @Builder.Default
    private List<Poi> pois = new ArrayList<>();
}
