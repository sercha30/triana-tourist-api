package com.salesianostriana.dam.TrianaTourist.category.model.dto;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetListCategoryDto {

    private UUID id;
    private String name;
}
