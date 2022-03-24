package com.example.demo.source.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@Builder
public class CustomerDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String age;
    private Date createdAt;
    private Date updatedAt;
}
