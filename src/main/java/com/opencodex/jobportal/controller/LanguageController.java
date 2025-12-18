package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.dto.language.LanguageRequest;
import com.opencodex.jobportal.dto.language.LanguageResponse;
import com.opencodex.jobportal.entity.Language;
import com.opencodex.jobportal.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService service;

    // Public
    @GetMapping
    public List<LanguageResponse> getAll() {
        return service.getAllLanguages();
    }

    @GetMapping("/{id}")
    public LanguageResponse getById(@PathVariable UUID id) {
        return service.getLanguageById(id);
    }

    // ADMIN Only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public LanguageResponse createLanguage(@Valid @RequestBody LanguageRequest request) {
        return  service.createLanguage(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public LanguageResponse update(@PathVariable UUID id, @Valid @RequestBody LanguageRequest request) {
        return service.updateLanguage(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteLanguage(id);
    }
}
