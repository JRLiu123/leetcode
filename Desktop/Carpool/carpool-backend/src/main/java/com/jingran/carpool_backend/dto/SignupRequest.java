package com.jingran.carpool_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Email
    public String username;

    @NotBlank
    public String name;

    @NotBlank
    @Size(min = 8)
    public String password;

    @NotBlank
    @JsonProperty("confirm password")
    public String confirmPassword;

}
