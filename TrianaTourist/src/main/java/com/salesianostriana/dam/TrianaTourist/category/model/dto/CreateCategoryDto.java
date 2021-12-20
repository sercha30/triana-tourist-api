package com.salesianostriana.dam.TrianaTourist.category.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateCategoryDto {

    @NotEmpty
    @Column(unique = true)
    private String name;

}
