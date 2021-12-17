package com.salesianostriana.dam.TrianaTourist.category.model;

import com.salesianostriana.dam.TrianaTourist.poi.model.Poi;
import lombok.*;

import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Category implements Serializable {

    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "categories")
    private List<Poi> pois = new ArrayList<>();
}
