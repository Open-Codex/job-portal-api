package com.opencodex.jobportal.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "The name is required")
    @Size(max = 100, message = "The name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "The email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "The email cannot be longer than 100 characters")
    private String email;

    @NotBlank(message = "The password is required")
    @Size(min = 6, max = 100, message = "The password must be at least 6 characters long.")
    private String password;
}
