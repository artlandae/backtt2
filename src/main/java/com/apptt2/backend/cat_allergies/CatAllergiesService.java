package com.apptt2.backend.cat_allergies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatAllergiesService {

    @Autowired
    private CatAllergiesRepository catAllergiesRepository;

    public List<CatAllergies> findAll() {
        return catAllergiesRepository.findAll();
    }

    public Optional<CatAllergies> findById(int id) {
        return catAllergiesRepository.findById(id);
    }

    public CatAllergies save(CatAllergies catAllergies) {
        return catAllergiesRepository.save(catAllergies);
    }

    public void deleteById(int id) {
        catAllergiesRepository.deleteById(id);
    }

    // Puedes agregar más métodos según sea necesario
}
