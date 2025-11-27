package com.opencodex.jobportal.dto.skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillRequest {

    @NotBlank(message = "The skill name is required")
    @Size(max = 100, message = "The skill name cannot exceed 100 characters")
    private String name;
}
