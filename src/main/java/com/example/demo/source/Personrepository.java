package com.example.demo.source;


import org.springframework.data.jpa.repository.JpaRepository;


public interface Personrepository extends JpaRepository<Person, Long> {
}
