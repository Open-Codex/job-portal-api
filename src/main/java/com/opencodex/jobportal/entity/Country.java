package com.opencodex.jobportal.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;
}
