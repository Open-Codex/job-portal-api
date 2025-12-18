package com.opencodex.jobportal.dto.language;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageRequest {

    @NotBlank(message = "The language code is required")
    @Size(max = 10, message = "The code cannot exceed 10 characters")
    private String code;

    @NotBlank(message = "The language name is required")
    @Size(max = 100, message = "The name cannot exceed 100 characters")
    private String name;
}
