package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.dto.country.CountryRequest;
import com.opencodex.jobportal.dto.country.CountryResponse;
import com.opencodex.jobportal.entity.Country;
import com.opencodex.jobportal.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    // Public
    @GetMapping
    public List<CountryResponse> getAll() {
        return service.getAllCountries();
    }

    @GetMapping("/{id}")
    public CountryResponse getById(@PathVariable UUID id) {
        return service.getCountryById(id);
    }

    // ADMIN Only

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CountryResponse createCountry(@Valid @RequestBody CountryRequest request) {
        return service.createCountry(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CountryResponse update(@PathVariable UUID id, @Valid @RequestBody CountryRequest request) {
        return service.updateCountry(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteCountry(id);
    }
}
