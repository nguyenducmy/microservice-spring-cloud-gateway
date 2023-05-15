package com.example.gatewayapi.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
