package com.example.hospital.entities;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegistrationDto {
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;
}