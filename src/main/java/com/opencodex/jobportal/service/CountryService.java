package com.opencodex.jobportal.service;

import com.opencodex.jobportal.entity.Country;
import com.opencodex.jobportal.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getByName(String name) {
        return countryRepository.findByName(name);
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(UUID id) {
        countryRepository.deleteById(id);
    }
}
