package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.dto.joboffer.JobOfferRequest;
import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import com.opencodex.jobportal.service.JobOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

//    @GetMapping
//    public List<JobOfferResponse> getAll() {
//        return jobOfferService.getAllJobOffersActive();
//    }

    @GetMapping("/{id}")
    public JobOfferResponse getById(@PathVariable UUID id) {
        return jobOfferService.getJobOfferById(id);
    }

    @GetMapping
    public Page<JobOfferResponse> searchJobOffers(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID countryId,
            @RequestParam(required = false) SeniorityEnum seniority,
            @RequestParam(required = false) LocationTypeEnum locationType,
            Pageable pageable
    ) {
        return jobOfferService.searchJobOffers(
                categoryId,
                countryId,
                seniority,
                locationType,
                pageable
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public JobOfferResponse create(
            @Valid @RequestBody JobOfferRequest request,
            Authentication authentication
    ) {
        return jobOfferService.createJobOffer(request, authentication.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public JobOfferResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody JobOfferRequest request,
            Authentication authentication
    ) {
        return jobOfferService.updateJobOffer(id, request, authentication.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        jobOfferService.deactivateJobOffer(id, authentication.getName());
    }
}
