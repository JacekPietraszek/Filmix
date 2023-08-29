package pl.wasko.filmixbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.repository.MovieRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    @DisplayName("check if search by Id works correct")
    void getMovieById() {
        Long movieId = 1L;

        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film",120,
                0, null, null, null, null, null, null);

        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie1));


        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.ifPresent(movie -> assertEquals(movieId, movie.getId()));
    }

//    @Test
//    void getAllMovies() {
//
//        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film", 120,
//                0, null, null, null, null, null, null);
//
//        Movie movie2 = new Movie(2L, "Pinokio", LocalDate.now(), "Fajny film",120,
//                0, null, null, null, null, null, null);
//
//        List<Movie> movieList = Arrays.asList(movie1, movie2);
//
//        when(movieService.getAllMovies()).thenReturn(movieList);
//
//        assertEquals(movieList.size(), movieService.getAllMovies().size());
//    }

    @Test
    void getMovieByTitle() {
        String title = "Król lew";
        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film", 120,
                0, null, null, null, null, null, null);

        Mockito.when(movieService.getMovieByTitle(title)).thenReturn(Optional.of(movie1));

        Optional<Movie> optionalMovie = movieService.getMovieByTitle(title);
        optionalMovie.ifPresent(movie -> assertEquals(1, movie.getId()));
    }
}