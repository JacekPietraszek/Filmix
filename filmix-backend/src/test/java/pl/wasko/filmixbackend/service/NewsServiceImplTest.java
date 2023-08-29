package pl.wasko.filmixbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.wasko.filmixbackend.model.DTO.NewsDTO;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.model.News;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsServiceImplTest {

    @Test
    @DisplayName("create new News DTO")
    void create_new_news_DTO() throws InstantiationException, IllegalAccessException {
        NewsService mockNewsService = mock(NewsService.class);
        Movie movie = new Movie();
        movie.setTitle("test title");
        NewsDTO dto = new NewsDTO(movie,"test content");

        when(mockNewsService.createNews(dto)).thenReturn(dto);

        NewsDTO result = mockNewsService.createNews(dto);

        assertEquals(result.getMovie(),dto.getMovie());
        assertEquals(result.getContent(),dto.getContent());
        assertSame(dto,result);
    }

    @Test
    @DisplayName("should return all news from the database")
    void findAll_should_return_all_news() {
        // Given
        List<News> newsList = Arrays.asList(
                new News(1L, "News 1"),
                new News(2L, "News 2"),
                new News(3L, "News 3")
        );

        NewsService mockNewsService = mock(NewsService.class);
        when(mockNewsService.findAll()).thenReturn(newsList);

        // When
        List<News> result = mockNewsService.findAll();

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
        NewsService mockNewsService = mock(NewsService.class);

        // When
        when(mockNewsService.findById(anyLong())).thenAnswer(inv -> {
            Long id = inv.getArgument(0);
            return newsList.stream()
                    .filter(news -> Objects.equals(news.getId(), id))
                    .findFirst();
        });

        Optional<News> result = mockNewsService.findById(1L);

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

        NewsService mockNewsService = mock(NewsService.class);

        // When
        when(mockNewsService.findNewsByCreatedAt(any(LocalDateTime.class))).thenReturn(Optional.of(expectedNews));
        Optional<News> result = mockNewsService.findNewsByCreatedAt(createdAt);

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
        NewsService mockNewsService = mock(NewsService.class);

        Long id = 2L;

        // When
        when(mockNewsService.existsById(id)).thenReturn(true);

        boolean tmp = newsList.stream()
                .anyMatch(news -> Objects.equals(news.getId(), id));
        boolean result = mockNewsService.existsById(id);
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

        NewsService mockNewsService = mock(NewsService.class);
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            newsList.removeIf(news -> Objects.equals(news.getId(), id));
            return null;
        }).when(mockNewsService).deleteById(anyLong());

        // When
        mockNewsService.deleteById(2L);

        // Then
        assertEquals(2, newsList.size());
        assertFalse(newsList.stream().anyMatch(news -> news.getId() == 2));
    }

    @Test
    @DisplayName("saving new News object")
    void save() {
        // Given
        NewsService mockNewsService = mock(NewsService.class);

        News news = new News(1L, "News 1");
        // When
        when(mockNewsService.save(news)).thenReturn(news);

        News result = mockNewsService.save(news);
        // Then
        assertSame(result, news);
    }
}