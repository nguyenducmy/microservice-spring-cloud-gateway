package com.example.authenticate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Transactional
@ToString(exclude="roles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;



}
