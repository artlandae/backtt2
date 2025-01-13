package com.apptt2.backend.cat_allergies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat-allergies")
public class CatAllergiesController {

    @Autowired
    private CatAllergiesService catAllergiesService;

    @GetMapping
    public List<CatAllergies> getAll() {
        return catAllergiesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatAllergies> getById(@PathVariable int id) {
        return catAllergiesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CatAllergies create(@RequestBody CatAllergies catAllergies) {
        return catAllergiesService.save(catAllergies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatAllergies> update(@PathVariable int id, @RequestBody CatAllergies catAllergies) {
        if (!catAllergiesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        catAllergies.setId(id);
        return ResponseEntity.ok(catAllergiesService.save(catAllergies));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!catAllergiesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        catAllergiesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}