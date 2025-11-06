package com.mohamedFarag.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @Size(min = 1, max = 20, message = "name field length has to be between 1 and 20")
    private String name;
    @Email(message = "Please provide a valid Email")
    private String email;
    @Size(min = 6, max = 20, message = "name field length has to be between 1 and 20")
    private String password;
}
