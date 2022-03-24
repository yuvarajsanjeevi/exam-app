package com.example.demo.source.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Objects;

/**
 * Added default constructor for jpa
 * Added equals and hashcode method
 * Add missing SEQUENCE strategy for id
 * Rename FirstName and LastName
 *
 *
 * Suggestion:
 * Need to have created_at, update at
 *
 */
@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue = 1, allocationSize = 20)
    private Long id;
    private  String firstName;
    private  String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof Person)) return false;

        Person other = (Person) object;
        return Objects.equals(getId(), other.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
