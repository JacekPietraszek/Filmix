package pl.wasko.filmixbackend.repository;

import pl.wasko.filmixbackend.model.News;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class NewsRepositoryTest {

    @Test
    @DisplayName("should return all news from the database")
    void findAll_should_return_all_news() {
        // Given
        List<News> newsList = Arrays.asList(
                new News(1L, "News 1"),
                new News(2L, "News 2"),
                new News(3L, "News 3")
        );

        NewsRepository mockNewsRepository = mock(NewsRepository.class);
        when(mockNewsRepository.findAll()).thenReturn(newsList);

        // When
        List<News> result = mockNewsRepository.findAll();

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsAll(newsList));
    }


    @Test
    @DisplayName("find news by id")
    void findById_shouldFindNewsById() {
        // Given
        List<News> newsList = Arrays.asList(
                new News(1L, "News 1"),
                new News(2L, "News 2"),
                new News(3L, "News 3")
        );
        NewsRepository mockNewsRepository = mock(NewsRepository.class);

        // When
        when(mockNewsRepository.findById(anyLong())).thenAnswer(inv -> {
            Long id = inv.getArgument(0);
            return newsList.stream()
                    .filter(news -> Objects.equals(news.getId(), id))
                    .findFirst();
        });

        Optional<News> result = mockNewsRepository.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("News 1", result.get().getContent());
    }


    @Test
    @DisplayName("returns news with the same value in createdAt")
    void findNewsByCreatedAt_return_news_with_same_createdAt() {
        // Given
        LocalDateTime createdAt = LocalDateTime.now();
        News expectedNews = new News(1L, "News");
        expectedNews.setCreatedAt(createdAt);

        NewsRepository mockNewsRepository = mock(NewsRepository.class);

        // When
        when(mockNewsRepository.findNewsByCreatedAt(any(LocalDateTime.class))).thenReturn(Optional.of(expectedNews));
        Optional<News> result = mockNewsRepository.findNewsByCreatedAt(createdAt);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedNews, result.get());
    }


    @Test
    @DisplayName("checks if there is news with the given id")
    void existsById_return_boolean() {
        // Given
        List<News> newsList = Arrays.asList(
                new News(1L, "News 1"),
                new News(2L, "News 2"),
                new News(3L, "News 3")
        );
        NewsRepository mockNewsRepository = mock(NewsRepository.class);

        Long id = 2L;

        // When
        when(mockNewsRepository.existsById(id)).thenReturn(true);

        boolean tmp = newsList.stream()
                .anyMatch(news -> Objects.equals(news.getId(), id));
        boolean result = mockNewsRepository.existsById(id);
        // Then
        assertEquals(tmp, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("should delete News by id")
    void deleteNewsById() {
        // Given
        List<News> newsList = new ArrayList<>();
        newsList.add(new News(1L, "News 1"));
        newsList.add(new News(2L, "News 2"));
        newsList.add(new News(3L, "News 3"));

        NewsRepository mockNewsRepository = mock(NewsRepository.class);
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            newsList.removeIf(news -> Objects.equals(news.getId(), id));
            return null;
        }).when(mockNewsRepository).deleteById(anyLong());

        // When
        mockNewsRepository.deleteById(2L);

        // Then
        assertEquals(2, newsList.size());
        assertFalse(newsList.stream().anyMatch(news -> news.getId() == 2));
    }
}
