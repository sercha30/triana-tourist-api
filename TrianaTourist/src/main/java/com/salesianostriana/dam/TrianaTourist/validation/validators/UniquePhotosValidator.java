package com.salesianostriana.dam.TrianaTourist.validation.validators;

import com.salesianostriana.dam.TrianaTourist.validation.annotations.UniquePhotos;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhotosValidator implements ConstraintValidator<UniquePhotos, Object> {

    private String coverPhoto;
    private String photo2;
    private String photo3;

    @Override
    public void initialize(UniquePhotos constraintAnnotation) {
        this.coverPhoto = constraintAnnotation.coverPhoto();
        this.photo2 = constraintAnnotation.photo2();
        this.photo3 = constraintAnnotation.photo3();
    }

    @Override
    public boolean isValid(Object photo, ConstraintValidatorContext constraintValidatorContext) {
        String coverPhotoValue = (String) PropertyAccessorFactory.forBeanPropertyAccess(photo)
                .getPropertyValue(coverPhoto);
        String photo2Value = (String) PropertyAccessorFactory.forBeanPropertyAccess(photo)
                .getPropertyValue(photo2);
        String photo3Value = (String) PropertyAccessorFactory.forBeanPropertyAccess(photo)
                .getPropertyValue(photo3);

        if(photo2Value != null) {
            if(photo3Value != null) {
                return !coverPhotoValue.equals(photo2Value) || !coverPhotoValue.equals(photo3Value);
            } else return !coverPhotoValue.equals(photo2Value);
        }

        return true;
    }
}
