package com.apptt2.backend.allergies_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allergies-users")
public class AllergiesUserController {

    @Autowired
    private AllergiesUserService AllergiesUserService;

    @GetMapping
    public List<AllergiesUser > getAll() {
        return AllergiesUserService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllergiesUser > getById(@PathVariable int id) {
        return AllergiesUserService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AllergiesUser  create(@RequestBody AllergiesUser  allergiesUser ) {
        return AllergiesUserService.save(allergiesUser );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AllergiesUser > update(@PathVariable int id, @RequestBody AllergiesUser  allergiesUser ) {
        if (!AllergiesUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        allergiesUser .setId(id);
        return ResponseEntity.ok(AllergiesUserService.save(allergiesUser ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!AllergiesUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AllergiesUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}