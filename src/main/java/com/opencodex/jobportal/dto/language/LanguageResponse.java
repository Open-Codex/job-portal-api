package com.opencodex.jobportal.dto.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageResponse {

    private UUID id;
    private String code;
    private String name;
}
