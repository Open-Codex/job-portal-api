package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.dto.joboffer.JobOfferRequest;
import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.service.JobOfferService;
import com.opencodex.jobportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<JobOfferResponse> getAll() {
        return jobOfferService.getAllJobOffersActive();
    }

    @GetMapping("/{id}")
    public JobOfferResponse getById(@PathVariable UUID id) {
        return jobOfferService.getJobOfferById(id);
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
