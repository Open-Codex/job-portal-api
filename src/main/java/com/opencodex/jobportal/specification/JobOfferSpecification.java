package com.opencodex.jobportal.specification;

import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.enums.LocationTypeEnum;
import com.opencodex.jobportal.enums.SeniorityEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class JobOfferSpecification {

    public static Specification<JobOffer> isActive() {
        return (root, query, cb) ->
                cb.isTrue(root.get("active"));
    }

    public static Specification<JobOffer> hasCategory(UUID categoryId) {
        return (root, query, cb) ->
                cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<JobOffer> hasCountry(UUID countryId) {
        return (root, query, cb) ->
                cb.equal(root.get("country").get("id"), countryId);
    }

    public static Specification<JobOffer> hasSeniority(SeniorityEnum seniority) {
        return (root, query, cb) ->
                cb.equal(root.get("seniority"), seniority);
    }

    public static Specification<JobOffer> hasLocationType(LocationTypeEnum locationType) {
        return (root, query, cb) ->
                cb.equal(root.get("locationType"), locationType);
    }
}
