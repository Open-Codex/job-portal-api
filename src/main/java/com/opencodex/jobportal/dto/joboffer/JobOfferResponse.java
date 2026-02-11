package com.opencodex.jobportal.dto.joboffer;

import com.opencodex.jobportal.enums.ContractTypeEnum;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SalaryTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferResponse {

    private UUID id;

    private String title;

    private String description;

    private BigDecimal salary;

    private SalaryTypeEnum salaryType;

    private String companyName;

    private String companyImageUrl;

    private String applicationLink;

    private String applicationEmail;

    private ContractTypeEnum contractType;

    private SeniorityEnum seniority;

    private LocationTypeEnum locationType;

    private String category;

    private String country;

    private String language;

    private Set<String> skills;

    private Boolean active;

    private LocalDateTime createdAt;
}
