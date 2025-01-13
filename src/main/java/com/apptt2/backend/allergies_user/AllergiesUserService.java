package com.apptt2.backend.allergies_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergiesUserService {

    @Autowired
    private AllergiesUserRepository AllergiesUserRepository;

    public List<AllergiesUser > findAll() {
        return AllergiesUserRepository.findAll();
    }

    public Optional<AllergiesUser > findById(int id) {
        return AllergiesUserRepository.findById(id);
    }

    public AllergiesUser  save(AllergiesUser  allergiesUser ) {
        return AllergiesUserRepository.save(allergiesUser );
    }

    public void deleteById(int id) {
        AllergiesUserRepository.deleteById(id);
    }

    // Puedes agregar más métodos según sea necesario
}
