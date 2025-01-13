package com.apptt2.backend.cat_criticall_illnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat-critical-illnes")
public class CatCriticalIllnesController {

    @Autowired
    private CatCriticalIllnesService catCriticalIllnesService;

    @GetMapping
    public List<CatCriticalIllnes> getAll() {
        return catCriticalIllnesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatCriticalIllnes> getById(@PathVariable int id) {
        return catCriticalIllnesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CatCriticalIllnes create(@RequestBody CatCriticalIllnes catCriticalIllnes) {
        return catCriticalIllnesService.save(catCriticalIllnes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatCriticalIllnes> update(@PathVariable int id, @RequestBody CatCriticalIllnes catCriticalIllnes) {
        if (!catCriticalIllnesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        catCriticalIllnes.setId(id);
        return ResponseEntity.ok(catCriticalIllnesService.save(catCriticalIllnes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!catCriticalIllnesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        catCriticalIllnesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
