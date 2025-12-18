package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.language.LanguageRequest;
import com.opencodex.jobportal.dto.language.LanguageResponse;
import com.opencodex.jobportal.entity.Language;
import com.opencodex.jobportal.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LanguageService {

    LanguageResponse createLanguage(LanguageRequest request);

    LanguageResponse getLanguageById(UUID id);

    List<LanguageResponse> getAllLanguages();

    LanguageResponse updateLanguage(UUID id, LanguageRequest request);

    void deleteLanguage(UUID id);
}
