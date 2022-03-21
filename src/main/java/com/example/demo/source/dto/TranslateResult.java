package com.example.demo.source.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslateResult {
    private Integer id;
    private String name;
}
