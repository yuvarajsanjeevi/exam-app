package com.example.demo.source.exception;

import com.example.demo.source.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final String CUSTOMER_001_ERROR = "customer_001";
    private static final String CUSTOMER_002_ERROR = "customer_002";

    @ExceptionHandler(value = CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult blogNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return buildErrorResult(customerNotFoundException.getMessage(), CUSTOMER_001_ERROR, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleGenericException(Exception exception) {
        return buildErrorResult(exception.getMessage(), CUSTOMER_002_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ErrorResult buildErrorResult(String message, String code, HttpStatus httpStatus) {
        return ErrorResult.builder()
                .code(code)
                .message(message)
                .timestamp(new Date())
                .status(httpStatus.value())
                .path(getUriPath())
                .build();
    }

    private String getUriPath() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return httpServletRequest.getRequestURI();
    }
}
