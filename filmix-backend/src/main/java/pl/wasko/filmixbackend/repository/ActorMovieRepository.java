package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.filmixbackend.model.ActorMovie;

public interface ActorMovieRepository extends JpaRepository<ActorMovie, Long> {

}
