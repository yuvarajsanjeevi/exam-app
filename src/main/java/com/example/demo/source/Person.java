package com.example.demo.source;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue()
    private Long id;
    private  String FirstName;
    private  String LastName;


    public Person(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
}
