package com.opencodex.jobportal.repository;

import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {
    List<JobOffer> findByUser(User user);
    List<JobOffer> findByActiveTrue();
    List<JobOffer> findByCategory_Id(UUID categoryId);
    List<JobOffer> findByCountry_Id(UUID countryId);
    List<JobOffer> findBySeniority(Enum seniority);
}
