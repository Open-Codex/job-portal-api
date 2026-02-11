package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.joboffer.JobOfferRequest;
import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface JobOfferService {

    JobOfferResponse createJobOffer(JobOfferRequest request, String userEmail);

    JobOfferResponse getJobOfferById(UUID id);

    List<JobOfferResponse> getAllJobOffersActive();

    JobOfferResponse updateJobOffer(UUID id, JobOfferRequest request, String userEmail);

    void deactivateJobOffer(UUID id, String userEmail);
}
