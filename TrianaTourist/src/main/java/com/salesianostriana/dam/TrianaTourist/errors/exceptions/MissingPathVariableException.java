package com.salesianostriana.dam.TrianaTourist.errors.exceptions;

public class MissingPathVariableException extends RuntimeException{

    public MissingPathVariableException() {
        super("Debe indicar el ID de la entidad en cuestión");
    }
}
