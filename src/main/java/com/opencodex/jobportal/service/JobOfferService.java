package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.joboffer.JobOfferRequest;
import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface JobOfferService {

    JobOfferResponse createJobOffer(JobOfferRequest request, String userEmail);

    JobOfferResponse getJobOfferById(UUID id);

    List<JobOfferResponse> getAllJobOffersActive();

    Page<JobOfferResponse> searchJobOffers (
            UUID categoryId,
            UUID countryId,
            SeniorityEnum seniority,
            LocationTypeEnum locationType,
            Pageable pageable
    );

    JobOfferResponse updateJobOffer(UUID id, JobOfferRequest request, String userEmail);

    void deactivateJobOffer(UUID id, String userEmail);
}
