package com.opencodex.jobportal.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillResponse {

    private UUID id;
    private String name;
}
