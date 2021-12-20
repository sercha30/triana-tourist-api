package com.salesianostriana.dam.TrianaTourist.category.controller;

import com.salesianostriana.dam.TrianaTourist.category.model.dto.CreateCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetListCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetSingleCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<GetListCategoryDto>> listAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    /*
    La única forma que he conseguido que Spring capture mi excepción y no la propia de la anotación
    @PathVariable o @RequestBody era cambiando el método "required" a false
     */
    @GetMapping("/{id}")
    public ResponseEntity<GetSingleCategoryDto> findOneById(@PathVariable(required = false) UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GetSingleCategoryDto> createNew(@Valid @RequestBody(required = false) CreateCategoryDto newCategory) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSingleCategoryDto> editOne(@PathVariable(required = false) UUID id,
                                                        @Valid @RequestBody(required = false) CreateCategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.edit(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneById(@PathVariable(required = false) UUID id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
