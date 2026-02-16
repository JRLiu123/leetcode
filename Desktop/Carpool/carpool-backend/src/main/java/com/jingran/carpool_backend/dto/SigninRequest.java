package com.jingran.carpool_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SigninRequest {
    @NotBlank
    @Email
    public String username;

    @NotBlank
    public String password;
}
