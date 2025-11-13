package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.entity.Country;
import com.opencodex.jobportal.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) { this.service = service; }

    @GetMapping
    public List<Country> getAll() {
        return service.getAllCountries();
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return service.createCountry(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable UUID id) {
        service.deleteCountry(id);
    }
}
