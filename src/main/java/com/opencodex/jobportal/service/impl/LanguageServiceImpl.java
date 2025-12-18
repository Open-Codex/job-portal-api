package com.opencodex.jobportal.service.impl;

import com.opencodex.jobportal.dto.language.LanguageRequest;
import com.opencodex.jobportal.dto.language.LanguageResponse;
import com.opencodex.jobportal.entity.Language;
import com.opencodex.jobportal.repository.LanguageRepository;
import com.opencodex.jobportal.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    @Override
    public LanguageResponse createLanguage(LanguageRequest request) {
        if (repository.existsByCodeIgnoreCase(request.getCode())) {
            throw new IllegalArgumentException("Language code already exists");
        }

        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Language name already exists");
        }

        Language language = new Language();
        language.setCode(request.getCode().toLowerCase());
        language.setName(request.getName());

        return toResponse(repository.save(language));
    }

    @Override
    public LanguageResponse getLanguageById(UUID id) {
        Language language = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Language not found")
        );

        return toResponse(language);
    }

    @Override
    public List<LanguageResponse> getAllLanguages() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public LanguageResponse updateLanguage(UUID id, LanguageRequest request) {
        Language language = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Language not found")
        );

        if (repository.existsByCodeIgnoreCase(request.getCode()) && !language.getCode().equalsIgnoreCase(request.getCode())) {
            throw new IllegalArgumentException("Language code already taken");
        }

        if ((repository.existsByNameIgnoreCase(request.getName())) && !language.getName().equalsIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Language name already taken");
        }

        language.setCode(request.getCode().toLowerCase());
        language.setName(request.getName());

        return toResponse(repository.save(language));
    }

    @Override
    public void deleteLanguage(UUID id) {
        if  (!repository.existsById(id)) {
            throw new EntityNotFoundException("Language not found");
        }

        repository.deleteById(id);
    }

    private LanguageResponse toResponse(Language language) {
        return new LanguageResponse(
                language.getId(),
                language.getCode(),
                language.getName()
        );
    }
}
