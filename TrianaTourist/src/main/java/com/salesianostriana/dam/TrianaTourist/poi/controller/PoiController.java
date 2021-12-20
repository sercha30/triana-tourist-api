package com.salesianostriana.dam.TrianaTourist.poi.controller;

import com.salesianostriana.dam.TrianaTourist.poi.model.dto.CreatePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.EditPoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.GetListPoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.model.dto.GetSinglePoiDto;
import com.salesianostriana.dam.TrianaTourist.poi.service.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<GetSinglePoiDto> findOneById(@PathVariable(required = false) UUID id) {
        return ResponseEntity.ok(poiService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GetSinglePoiDto> createNew(@Valid @RequestBody(required = false) CreatePoiDto newPoi) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(poiService.save(newPoi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSinglePoiDto> editOne(@PathVariable(required = false) UUID id,
                                                   @Valid @RequestBody(required = false) EditPoiDto editPoiDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(poiService.edit(id, editPoiDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(required = false) UUID id) {
        poiService.delete(id);

        return ResponseEntity.noContent()
                .build();
    }

}
