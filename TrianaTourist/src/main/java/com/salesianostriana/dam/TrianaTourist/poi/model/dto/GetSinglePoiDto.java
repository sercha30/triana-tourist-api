package com.salesianostriana.dam.TrianaTourist.poi.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetSinglePoiDto {

    private UUID id;
    private String name;
    private String location;
    private String description;
    private LocalDate date;
    private String coverPhoto;
    private String photo2;
    private String photo3;

    @Builder.Default
    private List<String> categoriesName = new ArrayList<>();
}
