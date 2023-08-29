package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Optional<Actor> getActorById(Long id);

    List<Actor> getAllActors();

    Optional<Actor> getActorByFirstName(String firstName);

    ActorDTO createActor(ActorDTO toSave);

    void deleteById(Long id);
}
