package com.salesianostriana.dam.TrianaTourist.errors.exceptions;

public class NamedEntityNotFoundException extends EntityNotFoundException{

    public NamedEntityNotFoundException(String name, Class clazz) {
        super(String.format("No se puede encontrar una entidad del tipo %s con nombre: %s",
                clazz.getName(), name));
    }
}
