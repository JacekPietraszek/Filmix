package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.DTO.NewsDTO;
import pl.wasko.filmixbackend.model.News;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    NewsDTO createNews(NewsDTO newsDTO) throws InstantiationException, IllegalAccessException;
    List<News> findAll();
    Optional<News> findById(Long id);
    Optional<News> findNewsByCreatedAt(LocalDateTime createdAt);
    boolean existsById(Long id);
    void deleteById(Long id);
    <S extends News> S save(S entity);
}
