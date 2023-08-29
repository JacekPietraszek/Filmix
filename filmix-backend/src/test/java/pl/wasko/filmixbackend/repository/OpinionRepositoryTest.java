package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.Opinion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OpinionRepositoryTest {

    @Test
    @DisplayName("should return all opinions from the database")
    void findAll_should_return_all_opinions() {
        // Given
        List<Opinion> opinions = Arrays.asList(
                new Opinion(1L, LocalDateTime.now(), "Opinion 1"),
                new Opinion(2L, LocalDateTime.now(), "Opinion 2"),
                new Opinion(3L, LocalDateTime.now(), "Opinion 3")
        );

        OpinionRepository mockOpinionRepository = mock(OpinionRepository.class);
        when(mockOpinionRepository.findAll()).thenReturn(opinions);

        // When
        List<Opinion> result = mockOpinionRepository.findAll();

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsAll(opinions));
    }


    @Test
    @DisplayName("find opinion by id")
    void findById_shouldFindOpinionById() {
        // Given
        List<Opinion> opinions = Arrays.asList(
                new Opinion(1L, LocalDateTime.now(), "Opinion 1"),
                new Opinion(2L, LocalDateTime.now(), "Opinion 2"),
                new Opinion(3L, LocalDateTime.now(), "Opinion 3")
        );
        OpinionRepository mockOpinionRepository = mock(OpinionRepository.class);

        // When
        when(mockOpinionRepository.findById(anyLong())).thenAnswer(inv -> {
            long id = inv.getArgument(0);
            return opinions.stream()
                    .filter(opinion -> opinion.getId() == id)
                    .findFirst();
        });

        Optional<Opinion> result = mockOpinionRepository.findById(2L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Opinion 2", result.get().getOpinion());
    }

    @Test
    @DisplayName("returns opinions with the same value in created at")
    void findOpinionsByCreatedAt_return_opinions_with_same_createdAt() {
        // Given
        LocalDateTime createdAt = LocalDateTime.now();
        List<Opinion> expectedOpinions = Arrays.asList(
                new Opinion(1L, LocalDateTime.now(), "Opinion 1"),
                new Opinion(2L, LocalDateTime.now(), "Opinion 2"),
                new Opinion(3L, LocalDateTime.now(), "Opinion 3")
        );

        OpinionRepository mockOpinionRepository = mock(OpinionRepository.class);

        // When
        when(mockOpinionRepository.findOpinionsByCreatedAt(createdAt)).thenReturn(expectedOpinions);
        List<Opinion> result = mockOpinionRepository.findOpinionsByCreatedAt(createdAt);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(expectedOpinions, result);
    }


    @Test
    @DisplayName("checks if there is an opinion with the given id")
    void existsById_return_boolean() {
        // Given
        List<Opinion> opinions = Arrays.asList(
                new Opinion(1L, LocalDateTime.now(), "Opinion 1"),
                new Opinion(2L, LocalDateTime.now(), "Opinion 2"),
                new Opinion(3L, LocalDateTime.now(), "Opinion 3")
        );
        OpinionRepository mockOpinionRepository = mock(OpinionRepository.class);

        long id = 2;

        // When
        when(mockOpinionRepository.existsById(id)).thenReturn(true);

        boolean tmp = opinions.stream()
                .anyMatch(opinion -> opinion.getId() == id);
        boolean result =  mockOpinionRepository.existsById(id);
        // Then
        assertEquals(tmp, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("should delete Opinion by id")
    void deleteOpinionById() {
        // Given
        List<Opinion> opinions = new ArrayList<>();
        opinions.add(new Opinion(1L, LocalDateTime.now(), "Opinion 1"));
        opinions.add(new Opinion(2L, LocalDateTime.now(), "Opinion 2"));
        opinions.add(new Opinion(3L, LocalDateTime.now(), "Opinion 3"));

        OpinionRepository mockOpinionRepository = mock(OpinionRepository.class);
        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            opinions.removeIf(opinion -> opinion.getId() == id);
            return null;
        }).when(mockOpinionRepository).deleteById(anyLong());

        // When
        mockOpinionRepository.deleteById(4L);

        // Then
        assertEquals(3, opinions.size());
        assertFalse(opinions.stream().anyMatch(opinion -> opinion.getId() == 4));
    }





}