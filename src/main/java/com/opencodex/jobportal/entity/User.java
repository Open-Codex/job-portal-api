package com.opencodex.jobportal.entity;

import com.opencodex.jobportal.enums.RoleEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.type.SqlTypes;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role_enum", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private RoleEnum role = RoleEnum.USER;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
