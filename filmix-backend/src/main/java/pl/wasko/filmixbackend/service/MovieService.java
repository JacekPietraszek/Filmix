package pl.wasko.filmixbackend.service;


import pl.wasko.filmixbackend.model.DTO.MovieDTO;
import pl.wasko.filmixbackend.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Optional<Movie> getMovieById(Long id);

    List<Movie> getAllMovies();

    Optional<Movie> getMovieByTitle(String title);

    String createMovie(MovieDTO toSave);

    void deleteById(Long id);

    void addCategoryToMovie(Long movieId, Long categoryId);

}

