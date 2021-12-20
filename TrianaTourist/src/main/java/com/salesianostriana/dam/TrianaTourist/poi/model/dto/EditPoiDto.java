package com.salesianostriana.dam.TrianaTourist.poi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.TrianaTourist.validation.annotations.UniquePhotos;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@UniquePhotos.List(
        @UniquePhotos(
                coverPhoto = "coverPhoto",
                photo2 = "photo2",
                photo3 = "photo3"
        )
)
public class EditPoiDto {

    @NotEmpty(message = "{poi.name.empty}")
    private String name;

    @Pattern(regexp = "[0-9],[0-9]", message = "{poi.location.pattern}")
    private String location;

    private String description;

    @Past(message = "{poi.date.past}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private LocalDate date;

    @URL(message = "{poi.photo.url}")
    @NotBlank(message = "{poi.coverPhoto.blank}")
    private String coverPhoto;

    @URL(message = "{poi.photo.url}")
    private String photo2;

    @URL(message = "{poi.photo.url}")
    private String photo3;
}
