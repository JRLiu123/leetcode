package com.jingran.carpool_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    @Email
    public String email;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

}
