package com.example.demo.source.exception;

/**
 * @author yuvaraj sanjeevi
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
