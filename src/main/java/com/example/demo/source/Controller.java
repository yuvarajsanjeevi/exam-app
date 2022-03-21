package com.example.demo.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    Personrepository personrepository;

    @PostMapping("/customers")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        try {
            return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }


    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> persons = personrepository.findAll();
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @GetMapping("/dotranslate/{value}")
    public TranslateResult  doTranslade(@PathVariable Integer value) {

        TranslateResult result = new TranslateResult();
        result.setId(value);
        result.setName(InttoWord.Translate(value));

        return result;
    }

    @GetMapping("/sayhello/{value}")
    public SayhelloResult  sayhello(@PathVariable Integer value) {

        SayhelloResult result = new SayhelloResult();
        result.setId(value);
        result.setResult(Sayhello.SayXHello(value));

        return result;
    }
}
