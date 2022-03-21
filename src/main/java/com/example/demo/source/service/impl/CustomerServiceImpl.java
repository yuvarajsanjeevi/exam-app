package com.example.demo.source.service.impl;


import com.example.demo.source.dto.CustomerDTO;
import com.example.demo.source.entity.Customer;
import com.example.demo.source.exception.CustomerNotFoundException;
import com.example.demo.source.repository.CustomerRepository;
import com.example.demo.source.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService  {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {

        try {
            Customer newCustomer = Customer.builder()
                    .name(customerDTO.getName())
                    .age(customerDTO.getAge())
                    .build();
            newCustomer = customerRepository.save(newCustomer);
            customerDTO.setId(newCustomer.getId());
            customerDTO.setCreatedAt(newCustomer.getCreatedAt());
        } catch (DataAccessException dataAccessException) {
            log.error("Error while saving customer", dataAccessException);
           throw new RuntimeException("Unable to save customer");
        }

        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::buildCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer
                .map(this::buildCustomerDTO)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }


    private CustomerDTO buildCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
