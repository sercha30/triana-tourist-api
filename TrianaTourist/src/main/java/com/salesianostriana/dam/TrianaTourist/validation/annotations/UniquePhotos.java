package com.salesianostriana.dam.TrianaTourist.validation.annotations;

import com.salesianostriana.dam.TrianaTourist.validation.validators.UniquePhotosValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhotosValidator.class)
@Documented
public @interface UniquePhotos {

    String message() default "Las fotos deben ser todas distintas";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String coverPhoto();
    String photo2();
    String photo3();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        UniquePhotos[] value();
    }
}
