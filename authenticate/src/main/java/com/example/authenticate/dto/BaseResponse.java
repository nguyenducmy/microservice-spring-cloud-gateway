package com.example.authenticate.dto;

import lombok.Data;

@Data
public class BaseResponse {
    private String status;
    private String code;
    private Object object;
}
