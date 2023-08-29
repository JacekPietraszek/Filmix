package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Actor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Override
    List<Actor> findAll();

    Optional<Actor> findById(Long id);

    Optional<Actor> findByFirstName(String firstName);

    void deleteById(Long id);


}
