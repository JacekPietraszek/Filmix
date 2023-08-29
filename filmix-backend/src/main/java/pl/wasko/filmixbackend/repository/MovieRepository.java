package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById(Long id);

    List<Movie> findAll();

    Optional<Movie> findByTitle(String title);


    @Override
    <S extends Movie> S save(S entity);

    @Override
    void flush();
}
