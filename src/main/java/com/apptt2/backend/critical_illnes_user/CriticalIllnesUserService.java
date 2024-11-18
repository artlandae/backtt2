package com.apptt2.backend.critical_illnes_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriticalIllnesUserService {

    @Autowired
    private CriticalIllnesUserRepository criticalIllnesUserRepository;

    public List<CriticalIllnesUser > findAll() {
        return criticalIllnesUserRepository.findAll();
    }

    public Optional<CriticalIllnesUser > findById(int id) {
        return criticalIllnesUserRepository.findById(id);
    }

    public CriticalIllnesUser  save(CriticalIllnesUser  criticalIllnesUser ) {
        return criticalIllnesUserRepository.save(criticalIllnesUser );
    }

    public void deleteById(int id) {
        criticalIllnesUserRepository.deleteById(id);
    }

    // Puedes agregar más métodos según sea necesario
}