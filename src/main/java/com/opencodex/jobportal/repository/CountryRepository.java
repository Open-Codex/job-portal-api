package com.opencodex.jobportal.repository;

import com.opencodex.jobportal.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    Optional<Country> findByName(String name);
    boolean existsByNameIgnoreCase(String name);
}
