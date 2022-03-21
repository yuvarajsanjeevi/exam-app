
package com.example.demo.source.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorResult {

    private String code;
    private String message;
    private String path;
    private Integer status;
    private Date timestamp;

}
