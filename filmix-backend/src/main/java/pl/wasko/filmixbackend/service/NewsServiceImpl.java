package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.NewsNotFoundException;
import pl.wasko.filmixbackend.mapper.AutoNewsMapper;
import pl.wasko.filmixbackend.model.DTO.NewsDTO;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.model.News;
import pl.wasko.filmixbackend.repository.MovieRepository;
import pl.wasko.filmixbackend.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final MovieRepository movieRepository;


    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = AutoNewsMapper.MAPPER.mapToNews(newsDTO);
        Optional<Movie> movie = movieRepository.findById(newsDTO.getMovie().getId());
        if(movie.isPresent()){
            news.setMovie(movie.get());
        } else {
            /// TODO: 2023-07-27 ZWROCIC ZAMIAST TEGO WYJATEK 
            news.setMovie(new Movie());
        }
        news.setCreatedAt(LocalDateTime.now());
        News savedNews = newsRepository.save(news);
        return AutoNewsMapper.MAPPER.mapToNewsDTO(savedNews);
    }

    @Override
    public List<News> findAll() {

        return Optional.of(newsRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NewsNotFoundException("no news available"));
    }

    @Override
    public Optional<News> findById(Long id) {

        return Optional.ofNullable(newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("news with the given id doesnt exists")));
    }

    @Override
    public Optional<News> findNewsByCreatedAt(LocalDateTime createdAt) {

        return Optional.ofNullable(newsRepository.findNewsByCreatedAt(createdAt)
                .orElseThrow(() -> new NewsNotFoundException("news with the given date of created at doesnt exist")));
    }

    @Override
    public boolean existsById(Long id) {

        return Optional.of(newsRepository.existsById(id))
                .orElseThrow(() -> new NewsNotFoundException("news with the given id doesnt exist"));
    }

    @Override
    public void deleteById(Long id) {

        newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("news with the given id doesnt exist"));

        newsRepository.findById(id)
                .ifPresent(category -> newsRepository.deleteById(id));
    }

    @Override
    public <S extends News> S save(S entity) {
        S savedEntity = newsRepository.save(entity);
        return Optional.ofNullable(savedEntity)
                .orElseThrow(() -> new NewsNotFoundException("Failed to save the category"));
    }
}
