package com.salesianostriana.dam.TrianaTourist.poi.controller;

import com.salesianostriana.dam.TrianaTourist.category.model.dto.CreateCategoryDto;
import com.salesianostriana.dam.TrianaTourist.category.model.dto.GetListCategoryDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.CreatePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.EditPoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.GetListPoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.GetSinglePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.service.PoiService;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pois")
public class PoiController {

    private final PoiService poiService;

    @GetMapping("/")
    public ResponseEntity<List<GetListPoiDto>> listAll() {
        return ResponseEntity.ok(poiService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSinglePoiDto> findOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(poiService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GetSinglePoiDto> createNew(@RequestBody CreatePoiDto newPoi) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(poiService.save(newPoi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSinglePoiDto> editOne(@PathVariable UUID id,
                                                   @RequestBody EditPoiDto editPoiDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(poiService.edit(id, editPoiDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable UUID id) {
        poiService.delete(id);

        return ResponseEntity.noContent()
                .build();
    }

}
