package com.apptt2.backend.cat_criticall_illnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatCriticalIllnesService {

    @Autowired
    private CatCriticalIllnesRepository catCriticalIllnesRepository;

    public List<CatCriticalIllnes> findAll() {
        return catCriticalIllnesRepository.findAll();
    }

    public Optional<CatCriticalIllnes> findById(int id) {
        return catCriticalIllnesRepository.findById(id);
    }

    public CatCriticalIllnes save(CatCriticalIllnes catCriticalIllnes) {
        return catCriticalIllnesRepository.save(catCriticalIllnes);
    }

    public void deleteById(int id) {
        catCriticalIllnesRepository.deleteById(id);
    }

    // Puedes agregar más métodos según sea necesario
}