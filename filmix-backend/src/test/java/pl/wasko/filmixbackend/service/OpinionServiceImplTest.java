package pl.wasko.filmixbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.model.Opinion;
import pl.wasko.filmixbackend.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OpinionServiceImplTest {

    @Test
    @DisplayName("create new opinion DTO")
    void createOpinion_should_create_new_Opinion_DTO() {
        // Given
        OpinionService mockOpinionService = mock(OpinionService.class);
        User user = new User();
        OpinionDTO dto = new OpinionDTO(user,null, LocalDateTime.now(),null);

        // When
        when(mockOpinionService.createOpinion(dto)).thenReturn(dto);

        OpinionDTO result = mockOpinionService.createOpinion(dto);

        // Then

        assertSame(dto, result);
    }

    @Test
    @DisplayName("should return all opinions from the database")
    void findAll_should_return_all_opinions() {
        // Given
        OpinionService mockOpinionService = mock(OpinionService.class);


        List<OpinionDTO> opinionList = new ArrayList<>();
        OpinionDTO opinionDTO1 = new OpinionDTO();
        OpinionDTO opinionDTO2 = new OpinionDTO();
        opinionDTO1.setOpinion("Great!");
        opinionDTO2.setOpinion("Wow");
        opinionList.add(opinionDTO1);
        opinionList.add(opinionDTO2);

        // When
        when(mockOpinionService.findAll()).thenReturn(opinionList);

        List<OpinionDTO> result = mockOpinionService.findAll();

        // Then
        assertTrue(result.containsAll(opinionList));
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("find opinion by id")
    void findById_shouldFindOpinionById() {
        // Given
        OpinionService mockOpinionService = mock(OpinionService.class);
        List<Opinion> opinionList = Arrays.asList(
                new Opinion(1L, "Great movie!"),
                new Opinion(2L, "Average movie.")
        );

        // When
        when(mockOpinionService.findById(1L)).thenAnswer(inv -> {
            long id = inv.getArgument(0);
            return opinionList.stream()
                    .filter(opinion -> opinion.getId() == id)
                    .findFirst();
        });

        Optional<Opinion> result = mockOpinionService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Great movie!", result.get().getOpinion());

    }
    @Test
    @DisplayName("returns opinions with the same createdAt value")
    void findOpinionsByCreatedAt_return_opinions_with_same_createdAt() {
        // Given
        LocalDateTime expectedCreatedAt = LocalDateTime.of(2023, 7, 20, 12, 30);
        List<Opinion> expectedOpinions = Arrays.asList(
                new Opinion(1L, expectedCreatedAt, "This is a test opinion 1."),
                new Opinion(2L, expectedCreatedAt, "This is a test opinion 2.")
        );
        OpinionService mockOpinionService = mock(OpinionService.class);

        // When
        when(mockOpinionService.findOpinionsByCreatedAt(expectedCreatedAt)).thenReturn(expectedOpinions);
        List<Opinion> result = mockOpinionService.findOpinionsByCreatedAt(expectedCreatedAt);

        // Then
        assertFalse(result.isEmpty());
        assertEquals(expectedOpinions, result);
    }


    @Test
    @DisplayName("checks if there is an opinion with the given id")
    void existsById_return_boolean() {
        // Given
        List<Opinion> opinions = Arrays.asList(
                new Opinion(1L, "Great movie!"),
                new Opinion(2L, "Average movie."),
                new Opinion(3L, "Bad movie.")
        );
        OpinionService mockOpinionService = mock(OpinionService.class);

        long id = 2L;

        // When
        when(mockOpinionService.existsById(id)).thenReturn(true);

        boolean tmp = opinions.stream()
                .anyMatch(opinion -> opinion.getId() == id);
        boolean result =  mockOpinionService.existsById(id);

        // Then
        assertEquals(tmp, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("should delete Opinion by id")
    void deleteOpinionById() {
        // Given
        List<Opinion> opinions = new ArrayList<>();
        opinions.add(new Opinion(1L, "Great movie!" ));
        opinions.add(new Opinion(2L, "Average movie." ));
        opinions.add(new Opinion(3L, "Bad movie." ));

        OpinionService mockOpinionService = mock(OpinionService.class);
        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            opinions.removeIf(opinion -> opinion.getId() == id);
            return null;
        }).when(mockOpinionService).deleteById(anyLong());

        // When
        mockOpinionService.deleteById(2L);

        // Then
        assertEquals(2, opinions.size());
        assertFalse(opinions.stream().anyMatch(opinion -> opinion.getId() == 2));
    }

    @Test
    @DisplayName("saving new Opinion object")
    void save() {
        // Given
        OpinionService mockOpinionService = mock(OpinionService.class);
        Opinion opinion = new Opinion(1L, "Great movie!");

        // When
        when(mockOpinionService.save(opinion)).thenReturn(opinion);

        Opinion result = mockOpinionService.save(opinion);

        // Then
        assertSame(result, opinion);
    }
}
