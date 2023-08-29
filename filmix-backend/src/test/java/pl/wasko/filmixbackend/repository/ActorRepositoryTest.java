package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.Actor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class ActorRepositoryTest {

    @Mock
    private ActorRepository actorRepository;

    public ActorRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("check if findAll works correct")
    public void testFindAll() {

        //Data
        List<Actor> actors = Stream.of(
                new Actor(1L, "Jan", "Nowak", LocalDate.now(), null),
                new Actor(2L, "Agata", "Test", LocalDate.now(), null),
                new Actor(3L, "Jakub", "Kowal", LocalDate.now(), null)
        ).collect(Collectors.toList());

        when(actorRepository.findAll()).thenReturn(actors);


        assertEquals(actors.size(), actorRepository.findAll().size());
    }

    @Test
    @DisplayName("check if search by Id works correct")
    public void testFindById() {

        Long actorId = 1L;

        Actor actor1 = new Actor(1L, "Jan", "Nowak", LocalDate.now(), null);

        ActorRepository actorRepository = mock(ActorRepository.class);
        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actor1));

        Optional<Actor> optionalActor = actorRepository.findById(actorId);
        optionalActor.ifPresent(actor -> assertEquals("Jan", actor.getFirstName()));
    }

    @Test
    @DisplayName("check if search by username works correct")
    public void testFindByFirstName() {
        String firstName = "Jan";

        Actor actor1 = new Actor(1L, "Jan", "Nowak", LocalDate.now(), null);

        ActorRepository actorRepository = mock(ActorRepository.class);
        when(actorRepository.findByFirstName(firstName)).thenReturn(Optional.of(actor1));

        Optional<Actor> actor2 = actorRepository.findByFirstName(firstName);
        actor2.ifPresent(actor -> assertEquals(firstName, actor.getFirstName()));

    }

    @Test
    @DisplayName("check if deleted by id work")
    public void testDeleteActorById() {

        List<Actor> actors = Stream.of(
                new Actor(1L, "Jan", "Nowak", LocalDate.now(), null),
                new Actor(2L, "Agata", "Test", LocalDate.now(), null),
                new Actor(3L, "Jakub", "Kowal", LocalDate.now(), null)
        ).collect(Collectors.toList());

        ActorRepository actorRepository1 = mock(ActorRepository.class);
        doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            actors.removeIf(actor -> Objects.equals(actor.getId(), id));
            return null;
        }).when(actorRepository1).deleteById(anyLong());

        actorRepository1.deleteById(2L);

        assertEquals(2, actors.size());
        assertFalse(actors.stream().anyMatch(actor -> actor.getId() == 2));

    }
}
