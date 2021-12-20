package com.salesianostriana.dam.TrianaTourist.category.controller;

import com.salesianostriana.dam.TrianaTourist.category.model.dto.CreateCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.DtoCategoryConverter;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetListCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetSingleCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoCategoryConverter categoryConverter;

    @GetMapping("/")
    public ResponseEntity<List<GetListCategoryDto>> listAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleCategoryDto> findOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GetSingleCategoryDto> createNew(@RequestBody CreateCategoryDto newCategory) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSingleCategoryDto> editOne(@PathVariable UUID id,
                                                        @RequestBody CreateCategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.edit(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneById(@PathVariable UUID id) {
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
