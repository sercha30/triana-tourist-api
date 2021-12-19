package com.salesianostriana.dam.TrianaTourist.poi.model;

import com.salesianostriana.dam.TrianaTourist.category.model.Category;
import com.salesianostriana.dam.TrianaTourist.route.model.Route;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Poi implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private String name;
    private String location;
    private String description;
    private LocalDate date;
    private String coverPhoto;
    private String photo2;
    private String photo3;

    @Builder.Default
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "poi_id",
            foreignKey = @ForeignKey(name = "FK_CATEGORY_POI")),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    foreignKey = @ForeignKey(name = "FK_POI_CATEGORY")),
            name = "poi_category"
    )
    private List<Category> categories = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "poi_id",
                            foreignKey = @ForeignKey(name = "FK_ROUTE_POI")),
            inverseJoinColumns = @JoinColumn(name = "route_id",
                            foreignKey = @ForeignKey(name = "FK_POI_ROUTE")),
            name = "poi_route"
    )
    private List<Route> routes = new ArrayList<>();

    //HELPERS POI - CATEGORY ************************************

    public void addToCategory(Category c) {
        c.getPois().add(this);
        this.categories.add(c);
    }

    public void removeFromCategory(Category c) {
        c.getPois().remove(this);
        this.categories.remove(c);
    }

    //**********************************************************

    //HELPERS POI - ROUTE **************************************

    public void addToRoute(Route r) {
        r.getSteps().add(this);
        this.routes.add(r);
    }

    public void removeFromRoute(Route r) {
        r.getSteps().remove(this);
        this.routes.remove(r);
    }

    //**********************************************************
}
