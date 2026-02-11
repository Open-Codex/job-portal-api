package com.opencodex.jobportal.entity;

import com.opencodex.jobportal.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "job_offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type")
    private SalaryTypeEnum salaryType = SalaryTypeEnum.NONE;

    @Column(name = "company_name", length = 150)
    private String companyName;

    @Column(name = "company_image_url", columnDefinition = "TEXT")
    private String companyImageUrl;

    @Column(name = "application_link", columnDefinition = "TEXT")
    private String applicationLink;

    @Column(name = "application_email", length = 150)
    private String applicationEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractTypeEnum contractType = ContractTypeEnum.FULL_TIME;

    @Enumerated(EnumType.STRING)
    private SeniorityEnum seniority = SeniorityEnum.MID;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationTypeEnum locationType = LocationTypeEnum.REMOTE;

    private Boolean active = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Relations
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany
    @JoinTable(
            name = "job_offer_skills",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();
}
