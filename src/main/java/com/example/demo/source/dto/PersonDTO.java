package com.example.demo.source.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {

    private  Long id;
    private  String firstName;
    private  String lastName;

}
