package com.apptt2.backend.persona;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class personController {

    private final personService personService;

    @PostMapping
    public void createPersona(@RequestBody Person persona){

        personService.createPersona(persona);
    }
}
