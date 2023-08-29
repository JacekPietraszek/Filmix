package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.Movie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieRepositoryTest {

    @Mock
    private MovieRepository movieRepository;

    public MovieRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("check if findAll works correct")
    public void testFindAll() {

        //Data
        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film", 120,
                0, null, null, null, null, null, null);

        Movie movie2 = new Movie(2L, "Pinokio", LocalDate.now(), "Fajny film", 120,
                0, null, null, null, null, null, null);


        List<Movie> movieList = Arrays.asList(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(movieList);

        assertEquals(movieList.size(), movieRepository.findAll().size());
    }

    @Test
    @DisplayName("check if search by Id works correct")
    public void testFindById() {

        Long movieId = 1L;

        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film", 120,
                0, null, null, null, null, null, null);

        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie1));


        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.ifPresent(movie -> assertEquals(movieId, movie.getId()));
    }

    @Test
    @DisplayName("check if search by username works correct")
    public void testFindByFirstName() {
        String title = "Lion King";

        Movie movie1 = new Movie(1L, "Lion King", LocalDate.now(), "Fajny film", 120,
                0, null, null, null, null, null, null);

        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.findByTitle(title)).thenReturn(Optional.of(movie1));

        Optional<Movie> movie2 = movieRepository.findByTitle(title);
        movie2.ifPresent(movie -> assertEquals(title, movie.getTitle()));

    }
}
