package com.opencodex.jobportal.service;

import com.opencodex.jobportal.dto.joboffer.JobOfferResponse;
import com.opencodex.jobportal.entity.JobOffer;
import com.opencodex.jobportal.repository.JobOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobOfferServiceTest {

    @Mock
    private JobOfferRepository jobOfferRepository;

    @InjectMocks
    private JobOfferService jobOfferService;

    @Test
    void shouldReturnAllJobOffers() {
        List<JobOffer> mockList = List.of(new JobOffer(), new JobOffer());

        when(jobOfferRepository.findAll()).thenReturn(mockList);

        List<JobOfferResponse> result = jobOfferService.getAllJobOffersActive();

        assertEquals(2, result.size());
        verify(jobOfferRepository, times(1)).findAll();
    }
}
