package com.apptt2.backend.cat_role;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class catRoleService {

    @Autowired
    private catRoleRepository catRoleRepository;

    List<CatRole> getallCatRoles(){
        return catRoleRepository.findAll();
    }


}
