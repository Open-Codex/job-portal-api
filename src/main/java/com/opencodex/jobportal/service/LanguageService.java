package com.opencodex.jobportal.service;

import com.opencodex.jobportal.entity.Language;
import com.opencodex.jobportal.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getByCode(String code) {
        return languageRepository.findByCode(code);
    }

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(UUID id) {
        languageRepository.deleteById(id);
    }
}
