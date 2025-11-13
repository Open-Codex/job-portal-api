package com.opencodex.jobportal.repository;

import com.opencodex.jobportal.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language, UUID> {
    Optional<Language> findByCode(String code);
}
