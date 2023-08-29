package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.ActorNotFoundException;
import pl.wasko.filmixbackend.mapper.ActorMapper;
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;
import pl.wasko.filmixbackend.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public Optional<Actor> getActorByFirstName(String firstName) {
        return actorRepository.findByFirstName(firstName);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public ActorDTO createActor(ActorDTO toSave) {

        Actor actor = ActorMapper.INSTANCE.dtoToActor(toSave);

        Actor savedActor = actorRepository.save(actor);
        return ActorMapper.INSTANCE.actorToDTO(savedActor);
    }

    @Override
    public void deleteById(Long id) {
        actorRepository
                .findById(id)
                .ifPresentOrElse(
                        actor -> actorRepository.deleteById(id),
                        () -> {
                            throw new ActorNotFoundException("actor with the given id doesn't exist");
                        }
                );
    }
}
