package com.opencodex.jobportal.controller;

import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.entity.User;
import com.opencodex.jobportal.service.JobOfferService;
import com.opencodex.jobportal.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final UserService userService;

    public JobOfferController(JobOfferService jobOfferService, UserService userService) {
        this.jobOfferService = jobOfferService;
        this.userService = userService;
    }

    @GetMapping
    public List<JobOffer> getAllActiveOffers() {
        return jobOfferService.getAllActiveOffers();
    }

    @GetMapping("/{id}")
    public Optional<JobOffer> getOfferById(@PathVariable UUID id) {
        return jobOfferService.getOfferById(id);
    }

    @GetMapping("/user/{userId}")
    public List<JobOffer> getOffersByUser(@PathVariable UUID userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return jobOfferService.getOffersByUser(user);
    }

    @PostMapping
    public JobOffer createOffer(@RequestBody JobOffer offer) {
        return jobOfferService.createOffer(offer);
    }

    @PutMapping("/{id}/delete")
    public void deleteOffer(@PathVariable UUID id) {
        jobOfferService.deleteOffer(id);
    }
}
