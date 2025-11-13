package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.entity.Language;
import com.opencodex.jobportal.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageService service;

    public LanguageController(LanguageService service) { this.service = service; }

    @GetMapping
    public List<Language> getAll() {
        return service.getAllLanguages();
    }

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return service.createLanguage(language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable UUID id) {
        service.deleteLanguage(id);
    }
}
