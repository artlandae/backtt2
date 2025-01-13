package com.apptt2.backend.cat_role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catRoleRepository extends JpaRepository<CatRole, Integer>{

}
