package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.country.CountryRequest;
import com.opencodex.jobportal.dto.country.CountryResponse;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    CountryResponse createCountry(CountryRequest request);

    CountryResponse getCountryById(UUID id);

    List<CountryResponse> getAllCountries();

    CountryResponse updateCountry(UUID id, CountryRequest request);

    void deleteCountry(UUID id);
}