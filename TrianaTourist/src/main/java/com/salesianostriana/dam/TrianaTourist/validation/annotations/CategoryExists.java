package com.salesianostriana.dam.TrianaTourist.validation.annotations;

import com.salesianostriana.dam.TrianaTourist.validation.validators.CategoryExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryExistsValidator.class)
@Documented
public @interface CategoryExists {

    String message() default "La categoría debe existir";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
