package com.salesianostriana.dam.TrianaTourist.errors.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class ApiError {

    private HttpStatus status;
    private int code;
    private String message;
    private String route;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dateTime = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message, String route) {
        this.status = status;
        this.code = status.value();
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.route = route;
    }

    public ApiError(HttpStatus status, String message, String route, List<ApiSubError> subErrors) {
        this(status, message, route);
        this.subErrors = subErrors;
    }
}
