package com.apptt2.backend.persona;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class personService {

    private final personRepository PersonRepository;

    public void createPersona(Person persona){

        PersonRepository.save(persona);

    }

}
