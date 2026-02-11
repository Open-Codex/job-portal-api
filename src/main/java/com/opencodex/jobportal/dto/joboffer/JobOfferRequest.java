package com.opencodex.jobportal.dto.joboffer;

import com.opencodex.jobportal.enums.ContractTypeEnum;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SalaryTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferRequest {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    private String description;

    private BigDecimal salary;

    private SalaryTypeEnum salaryType;

    @Size(max = 150)
    private String CompanyName;

    private String companyImageUrl;

    private String applicationLink;

    private String applicationEmail;

    private ContractTypeEnum contractType;

    private SeniorityEnum seniority;

    private LocationTypeEnum locationType;

    private UUID categoryId;

    private UUID countryId;

    private UUID languageId;

    // 5 skills max rule
    @Size(max = 5, message = "You can only add up to 5 skills")
    private Set<UUID> skillIds;
}
