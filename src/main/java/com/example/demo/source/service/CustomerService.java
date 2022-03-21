package com.example.demo.source.service;


import com.example.demo.source.dto.CustomerDTO;
import com.example.demo.source.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);
}
