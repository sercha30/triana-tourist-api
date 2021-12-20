package com.salesianostriana.dam.TrianaTourist.errors.exceptions;

public class MissingRequestBodyException extends RuntimeException{

    public MissingRequestBodyException() {
        super("El cuerpo de la petición no puede estar vacío");
    }
}
