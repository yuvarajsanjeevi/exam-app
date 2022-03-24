package com.example.demo.source.service.impl;


import com.example.demo.source.dto.PersonDTO;
import com.example.demo.source.entity.Person;
import com.example.demo.source.repository.PersonRepository;
import com.example.demo.source.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;


    @Override
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::buildPersonDTO).collect(Collectors.toList());
    }


    private PersonDTO buildPersonDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }
}
