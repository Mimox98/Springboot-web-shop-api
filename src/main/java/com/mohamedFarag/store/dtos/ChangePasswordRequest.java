package com.mohamedFarag.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    @NotBlank(message = "Email can't be empty")
    @Size(min = 6, max = 20)
    private String newPassword;
}
