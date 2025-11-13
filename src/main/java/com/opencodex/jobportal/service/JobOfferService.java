package com.opencodex.jobportal.service;

import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    public List<JobOffer> getAllActiveOffers() {
        return jobOfferRepository.findByActiveTrue();
    }

    public Optional<JobOffer> getOfferById(UUID id) {
        return jobOfferRepository.findById(id);
    }

    public List<JobOffer> getOffersByUser(User user) {
        return jobOfferRepository.findByUser(user);
    }

    public JobOffer createOffer(JobOffer offer) {
        return jobOfferRepository.save(offer);
    }

    public JobOffer updateOffer(JobOffer offer) {
        return jobOfferRepository.save(offer);
    }

    public void deleteOffer(UUID id) {
        jobOfferRepository.deleteById(id);
    }
}
