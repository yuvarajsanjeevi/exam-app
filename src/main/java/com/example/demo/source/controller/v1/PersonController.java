package com.example.demo.source.controller.v1;

import com.example.demo.source.dto.PersonDTO;
import com.example.demo.source.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * changed get mapping value
 */
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;


    @GetMapping("/persons")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PersonDTO> getAllPersons() {

        return personService.getAllPersons();
    }
}
