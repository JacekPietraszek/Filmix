package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.ActorNotFoundException;
import pl.wasko.filmixbackend.model.DTO.MovieDTO;
import pl.wasko.filmixbackend.service.MovieService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return movieService
                .getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.createMovie(movieDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        try {
            movieService.deleteById(id);
            return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        } catch (ActorNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addMovies(@RequestBody List<MovieDTO> moviesDTO) {
        for (MovieDTO movieDTO : moviesDTO) {
            movieService.createMovie(movieDTO);
        }
        return ResponseEntity.ok(moviesDTO);
    }
    @PutMapping("/{movieId}/category/{categoryId}")
    public ResponseEntity<?> putCategoryIntoMovie(@PathVariable Long movieId,@PathVariable Long categoryId){
        movieService.addCategoryToMovie(movieId, categoryId);
        return ResponseEntity.ok().build();
    }
}
