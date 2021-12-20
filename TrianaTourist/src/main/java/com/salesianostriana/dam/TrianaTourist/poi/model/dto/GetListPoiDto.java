package com.salesianostriana.dam.TrianaTourist.poi.model.dto;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetListPoiDto {

    private UUID id;
    private String name;
    private String coverPhoto;
}
