package com.opencodex.jobportal.dto.auth;

import com.opencodex.jobportal.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;
    private String name;
    private String email;
    private RoleEnum role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
