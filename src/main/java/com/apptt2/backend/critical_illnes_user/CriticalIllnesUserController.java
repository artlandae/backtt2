package com.apptt2.backend.critical_illnes_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/critical-illnes-users")
public class CriticalIllnesUserController {

    @Autowired
    private CriticalIllnesUserService CriticalIllnesUserService;

    @GetMapping
    public List<CriticalIllnesUser > getAll() {
        return CriticalIllnesUserService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriticalIllnesUser > getById(@PathVariable int id) {
        return CriticalIllnesUserService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CriticalIllnesUser  create(@RequestBody CriticalIllnesUser  criticalIllnesUser ) {
        return CriticalIllnesUserService.save(criticalIllnesUser );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriticalIllnesUser > update(@PathVariable int id, @RequestBody CriticalIllnesUser  criticalIllnesUser ) {
        if (!CriticalIllnesUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        criticalIllnesUser .setId(id);
        return ResponseEntity.ok(CriticalIllnesUserService.save(criticalIllnesUser ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!CriticalIllnesUserService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CriticalIllnesUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
