package com.opencodex.jobportal.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;
}
