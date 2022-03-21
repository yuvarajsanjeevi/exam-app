package com.example.demo.source.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SayHelloResult {
    private Integer id;
    private String result;

}
