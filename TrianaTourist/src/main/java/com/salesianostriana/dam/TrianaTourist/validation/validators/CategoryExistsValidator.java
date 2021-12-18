package com.salesianostriana.dam.TrianaTourist.validation.validators;

import com.salesianostriana.dam.TrianaTourist.category.service.CategoryService;
import com.salesianostriana.dam.TrianaTourist.validation.annotations.CategoryExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, String> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(CategoryExists constraintAnnotation) { }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return categoryService.findCategoryByName(categoryName) != null;
    }
}
