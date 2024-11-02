package com.apptt2.backend.cat_role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class catRoleController {
    @Autowired
    private catRoleService catRoleService;

    // Obtener todos los roles
    @GetMapping
    public List<CatRole> getAllRoles() {
        return catRoleService.getallCatRoles();
    }

}
