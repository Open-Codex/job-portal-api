package com.opencodex.jobportal.service.impl;

import com.opencodex.jobportal.dto.country.CountryRequest;
import com.opencodex.jobportal.dto.country.CountryResponse;
import com.opencodex.jobportal.entity.Country;
import com.opencodex.jobportal.repository.CountryRepository;
import com.opencodex.jobportal.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    @Override
    public CountryResponse createCountry(CountryRequest request) {
        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Country already exists");
        }

        Country country = new Country();
        country.setName(request.getName());

        return toResponse(repository.save(country));
    }

    @Override
    public CountryResponse getCountryById(UUID id) {
        Country country = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Country not found")
        );

        return toResponse(country);
    }

    @Override
    public List<CountryResponse> getAllCountries() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CountryResponse updateCountry(UUID id, CountryRequest request) {
        Country country = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Country not found")
        );

        if (repository.existsByNameIgnoreCase(request.getName()) && !country.getName().equalsIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Country name already taken");
        }

        country.setName(request.getName());
        return toResponse(repository.save(country));
    }

    @Override
    public void deleteCountry(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Country not found");
        }

        repository.deleteById(id);
    }

    private CountryResponse toResponse(Country country) {
        return new CountryResponse(country.getId(), country.getName());
    }
}
