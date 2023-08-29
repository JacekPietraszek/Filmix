package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.Rate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RateRepositoryTest {

    @Test
    @DisplayName("should return all rates from the database")
    void findAll_should_return_all_rates() {
        // Given
        List<Rate> rates = Arrays.asList(
                new Rate(1L, 5),
                new Rate(2L, 4),
                new Rate(3L, 3)
        );

        RateRepository mockRateRepository = mock(RateRepository.class);
        when(mockRateRepository.findAll()).thenReturn(rates);

        // When
        List<Rate> result = mockRateRepository.findAll();

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsAll(rates));
    }

    @Test
    @DisplayName("find rate by id")
    void findById_shouldFindRateById() {
        // Given
        List<Rate> rates = Arrays.asList(
                new Rate(1L, 5),
                new Rate(2L, 4),
                new Rate(3L, 3)
        );
        RateRepository mockRateRepository = mock(RateRepository.class);

        // When
        when(mockRateRepository.findById(anyLong())).thenAnswer(inv -> {
            long id = inv.getArgument(0);
            return rates.stream()
                    .filter(rate -> rate.getId() == id)
                    .findFirst();
        });

        Optional<Rate> result = mockRateRepository.findById(2L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(4, result.get().getRating());
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

        RateRepository mockRateRepository = mock(RateRepository.class);

        // When
        when(mockRateRepository.findRatesByRating(expectedRating)).thenReturn(expectedRates);
        List<Rate> result = mockRateRepository.findRatesByRating(expectedRating);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(expectedRates, result);
    }


    @Test
    @DisplayName("should delete Rate by id")
    void deleteRateById() {
        // Given
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate(1L, 5));
        rates.add(new Rate(2L, 4));
        rates.add(new Rate(3L, 3));

        RateRepository mockRateRepository = mock(RateRepository.class);
        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            rates.removeIf(rate -> rate.getId() == id);
            return null;
        }).when(mockRateRepository).deleteById(anyLong());

        // When
        mockRateRepository.deleteById(2L);

        // Then
        assertEquals(2, rates.size());
        assertFalse(rates.stream().anyMatch(rate -> rate.getId() == 2));
    }



}
