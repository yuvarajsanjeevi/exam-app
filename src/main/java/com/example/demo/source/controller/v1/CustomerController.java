package com.example.demo.source.controller.v1;

import com.example.demo.source.dto.CustomerDTO;
import com.example.demo.source.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/customers")
@Slf4j
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerDTO save(@RequestBody @Valid CustomerDTO customerDTO) {

        return customerService.save(customerDTO);
    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }
}
