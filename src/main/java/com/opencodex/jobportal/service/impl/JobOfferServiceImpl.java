package com.opencodex.jobportal.service.impl;

import com.opencodex.jobportal.dto.joboffer.JobOfferRequest;
import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.Skill;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import com.opencodex.jobportal.repository.*;
import com.opencodex.jobportal.service.JobOfferService;
import com.opencodex.jobportal.specification.JobOfferSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final SkillRepository skillRepository;

    @Override
    public JobOfferResponse createJobOffer(JobOfferRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        JobOffer job = new JobOffer();
        mapFields(job, request);
        job.setUser(user);

        return toResponse(jobOfferRepository.save(job));
    }

    @Override
    public JobOfferResponse getJobOfferById(UUID id) {
        JobOffer job = jobOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));
        return toResponse(job);
    }

    @Override
    public List<JobOfferResponse> getAllJobOffersActive() {
        return jobOfferRepository.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Page<JobOfferResponse> searchJobOffers(
            UUID categoryId,
            UUID countryId,
            SeniorityEnum seniority,
            LocationTypeEnum locationType,
            Pageable pageable
    ) {
        Specification<JobOffer> spec = JobOfferSpecification.isActive();

        if (categoryId != null)
            spec = spec.and(JobOfferSpecification.hasCategory(categoryId));

        if (countryId != null)
            spec = spec.and(JobOfferSpecification.hasCountry(countryId));

        if (seniority != null)
            spec = spec.and(JobOfferSpecification.hasSeniority(seniority));

        if (locationType != null)
            spec = spec.and(JobOfferSpecification.hasLocationType(locationType));

        return jobOfferRepository.findAll(spec, pageable).map(this::toResponse);
    }

    @Override
    public JobOfferResponse updateJobOffer(UUID id, JobOfferRequest request, String userEmail) {

        JobOffer job = jobOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        validateOwner(job, userEmail);

        mapFields(job, request);

        return toResponse(jobOfferRepository.save(job));
    }

    @Override
    public void deactivateJobOffer(UUID id, String userEmail) {

        JobOffer job = jobOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        validateOwner(job, userEmail);

        job.setActive(false);
        jobOfferRepository.save(job);
    }

    // Helpers

    private void validateOwner(JobOffer job, String email) {
        if (!job.getUser().getEmail().equals(email)
                && !job.getUser().getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("Not authorized");
        }
    }

    private void mapFields(JobOffer job, JobOfferRequest request) {

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setSalaryType(request.getSalaryType());
        job.setCompanyName(request.getCompanyName());
        job.setCompanyImageUrl(request.getCompanyImageUrl());
        job.setApplicationLink(request.getApplicationLink());
        job.setApplicationEmail(request.getApplicationEmail());
        job.setContractType(request.getContractType());
        job.setSeniority(request.getSeniority());
        job.setLocationType(request.getLocationType());

        if (request.getCategoryId() != null)
            job.setCategory(categoryRepository.findById(request.getCategoryId()).orElse(null));

        if (request.getCountryId() != null)
            job.setCountry(countryRepository.findById(request.getCountryId()).orElse(null));

        if (request.getLanguageId() != null)
            job.setLanguage(languageRepository.findById(request.getLanguageId()).orElse(null));

        if (request.getSkillIds() != null) {
            Set<Skill> skills = request.getSkillIds().stream()
                    .map(id -> skillRepository.findById(id).orElseThrow())
                    .collect(Collectors.toSet());
            job.setSkills(skills);
        }
    }

    private JobOfferResponse toResponse(JobOffer job) {
        return new JobOfferResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSalary(),
                job.getSalaryType(),
                job.getCompanyName(),
                job.getCompanyImageUrl(),
                job.getApplicationLink(),
                job.getApplicationEmail(),
                job.getContractType(),
                job.getSeniority(),
                job.getLocationType(),
                job.getCategory() != null ? job.getCategory().getName() : null,
                job.getCountry() != null ? job.getCountry().getName() : null,
                job.getLanguage() != null ? job.getLanguage().getName() : null,
                job.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()),
                job.getActive(),
                job.getCreatedAt()
        );
    }
}
