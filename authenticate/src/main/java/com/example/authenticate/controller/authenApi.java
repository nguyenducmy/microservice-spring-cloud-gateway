package com.example.authenticate.controller;

import com.sun.jdi.request.StepRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authenApi {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
