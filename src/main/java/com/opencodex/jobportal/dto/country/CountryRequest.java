package com.opencodex.jobportal.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequest {

    @NotBlank(message = "The name is required")
    @Size(max = 100, message = "The name cannot exceed 100 characters")
    private String name;
}
