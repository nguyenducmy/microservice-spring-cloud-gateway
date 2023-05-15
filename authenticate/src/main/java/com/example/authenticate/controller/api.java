package com.example.authenticate.controller;

import com.example.authenticate.dto.BaseResponse;
import com.example.authenticate.dto.UserDto;
import com.example.authenticate.entity.User;
import com.example.authenticate.repository.UserRepository;
import com.example.authenticate.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authen")
public class api {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody User user){
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("200");
        baseResponse.setStatus("Register Done");
        return ResponseEntity.ok(baseResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserDto userDto){
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow();
        var jwt = "";
        if(user.getUsername().equals(userDto.getUsername())
                && new BCryptPasswordEncoder().matches(userDto.getPassword(), user.getPassword())){
            jwt = jwtService.generate(userDto.getUsername());
        }
        var baseResponse = new BaseResponse();
        baseResponse.setStatus("Login Successfull");
        baseResponse.setCode("200");
        baseResponse.setObject(jwt);
        return ResponseEntity.ok(baseResponse);
    }
}
