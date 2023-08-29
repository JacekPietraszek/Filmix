package pl.wasko.filmixbackend.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.controller.OpinionController;
import pl.wasko.filmixbackend.exception.ActorNotFoundException;
import pl.wasko.filmixbackend.exception.CategoryNotFoundException;
import pl.wasko.filmixbackend.exception.MovieNotFoundException;
import pl.wasko.filmixbackend.mapper.MovieMapper;
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.ActorMovie;
import pl.wasko.filmixbackend.model.Category;
import pl.wasko.filmixbackend.model.DTO.ActorMovieDTO;
import pl.wasko.filmixbackend.model.DTO.MovieDTO;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.repository.ActorMovieRepository;
import pl.wasko.filmixbackend.repository.CategoryRepository;
import pl.wasko.filmixbackend.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final ActorService actorService;
    private final ActorMovieRepository actorMovieRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }


    @Override
    public String createMovie(MovieDTO toSave) {
        Movie movie = MovieMapper.INSTANCE.dtoToMovie(toSave);

        Movie savedMovie = movieRepository.save(movie);

        Category firstCategory = categoryRepository.findById(toSave.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Not found"));
        movie.setCategory(firstCategory);

        List<Category> categoryList = new ArrayList<>();
        for (Category category : toSave.getMovieCategories()) {
            Category category1 = categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new CategoryNotFoundException("Not found"));
            categoryList.add(category1);
        }
        movie.setCategories(categoryList);
        // Save the Movie entity first


        // Iterate through the actorMovieList and manually set the relationships
        for (ActorMovieDTO actorMovieDTO : toSave.getActorMovieDtoList()) {
            Actor actor = actorService.getActorById(actorMovieDTO.getActorId())
                    .orElseThrow(() -> new ActorNotFoundException("Actor not found"));

            // Create a new ActorMovie entity and set the relationships
            ActorMovie managedActorMovie = new ActorMovie();
            managedActorMovie.setMovie(savedMovie); // Set the movie relationship
            managedActorMovie.setActor(actor);      // Set the actor relationship
            managedActorMovie.setRoleName(actorMovieDTO.getRoleName());

            // Save the ActorMovie entity
            actorMovieRepository.save(managedActorMovie);
        }

        return "Pomyślnie zapisano film " + movie.getTitle() + " w bazie danych.";
    }


    @Override
    public void deleteById(Long id) {
        movieRepository
                .findById(id)
                .ifPresentOrElse(
                        actor -> movieRepository.deleteById(id),
                        () -> {
                            throw new MovieNotFoundException("movie with the given id doesn't exist");
                        }
                );
    }

    @Override
    public void addCategoryToMovie(Long movieId, Long categoryId) {

    }


}


