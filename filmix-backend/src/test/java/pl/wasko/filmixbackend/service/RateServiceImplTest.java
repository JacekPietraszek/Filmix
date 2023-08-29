package pl.wasko.filmixbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RateServiceImplTest {

    @Test
    @DisplayName("create new rate DTO")
    void createRate_should_create_new_Rate_DTO() {
        // Given
        RateService mockRateService = mock(RateService.class);
        RateDTO dto = new RateDTO(null,null,null);

        // When
        when(mockRateService.createRate(dto)).thenReturn(dto);

        RateDTO result = mockRateService.createRate(dto);

        // Then
        assertEquals(result.getRating(), dto.getRating());
        assertSame(dto, result);
    }

    @Test
    @DisplayName("should return all rates from the database")
    void findAll_should_return_all_rates() {
        // Given
        RateService mockRateService = mock(RateService.class);

        List<Rate> rateList = Arrays.asList(
                new Rate(1L, 5),
                new Rate(2L, 4)
        );

        // When
        when(mockRateService.findAll()).thenReturn(rateList);

        List<Rate> result = mockRateService.findAll();

        // Then
        assertTrue(result.containsAll(rateList));
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("find rate by id")
    void findById_shouldFindRateById() {
        // Given
        RateService mockRateService = mock(RateService.class);
        List<Rate> rateList = Arrays.asList(
                new Rate(1L, 5),
                new Rate(2L, 4)
        );

        // When
        when(mockRateService.findById(1L)).thenAnswer(inv -> {
            long id = inv.getArgument(0);
            return rateList.stream()
                    .filter(rate -> rate.getId() == id)
                    .findFirst();
        });

        Optional<Rate> result = mockRateService.findById(1L);

        // Then
        assertEquals(5, result.get().getRating());
    }

    @Test
    @DisplayName("returns rates with the same value in rating")
    void findRatesByRating_return_rates_with_same_rating() {
        // Given
        int expectedRating = 5;
        List<Rate> expectedRates = Arrays.asList(
                new Rate(1L, expectedRating),
                new Rate(2L, expectedRating),
                new Rate(3L, expectedRating)
        );

        RateService mockRateRepository = mock(RateService.class);

        // When
        when(mockRateRepository.findRatesByRating(expectedRating)).thenReturn(expectedRates);
        List<Rate> result = mockRateRepository.findRatesByRating(expectedRating);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(expectedRates, result);
    }


    @Test
    @DisplayName("checks if there is a rate with the given id")
    void existsById_return_boolean() {
        // Given
        List<Rate> rates = Arrays.asList(
                new Rate(1L, 5),
                new Rate(2L, 4),
                new Rate(3L, 3)
        );
        RateService mockRateService = mock(RateService.class);

        long id = 2;

        // When
        when(mockRateService.existsById(id)).thenReturn(true);

        boolean tmp = rates.stream()
                .anyMatch(rate -> rate.getId() == id);
        boolean result =  mockRateService.existsById(id);

        // Then
        assertEquals(tmp, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("should delete Rate by id")
    void deleteRateById() {
        // Given
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate(1L, 5));
        rates.add(new Rate(2L, 4));
        rates.add(new Rate(3L, 3));

        RateService mockRateService = mock(RateService.class);
        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            rates.removeIf(rate -> rate.getId() == id);
            return null;
        }).when(mockRateService).deleteById(anyLong());

        // When
        mockRateService.deleteById(2L);

        // Then
        assertEquals(2, rates.size());
        assertFalse(rates.stream().anyMatch(rate -> rate.getId() == 2));
    }

    @Test
    @DisplayName("saving new Rate object")
    void save() {
        // Given
        RateService mockRateService = mock(RateService.class);
        Rate rate = new Rate(1L, 4);

        // When
        when(mockRateService.save(rate)).thenReturn(rate);

        Rate result = mockRateService.save(rate);

        // Then
        assertSame(result, rate);
    }
}
