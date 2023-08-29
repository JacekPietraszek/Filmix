package pl.wasko.filmixbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.mapper.ActorMapper;
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;
import pl.wasko.filmixbackend.repository.ActorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ActorServiceImplTest {
    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    public ActorServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("check if search by Id works correct")
    void getActorById() {
        Long actorId = 1L;
        Actor actor1 = new Actor(1L, "Jan", "Nowak", LocalDate.now(), null);

        Mockito.when(actorService.getActorById(actorId)).thenReturn(Optional.of(actor1));

        Optional<Actor> optionalActor = actorService.getActorById(actorId);
        optionalActor.ifPresent(actor -> assertEquals("Jan", actor.getFirstName()));
    }

    @Test
    @DisplayName("check if search by username works correct")
    void getActorByFirstName() {
        String firstName = "Jan";
        Actor actor1 = new Actor(1L, "Jan", "Nowak", LocalDate.now(), null);

        Mockito.when(actorService.getActorByFirstName(firstName)).thenReturn(Optional.of(actor1));

        Optional<Actor> optionalActor = actorService.getActorByFirstName(firstName);
        optionalActor.ifPresent(actor -> assertEquals(1, actor.getId()));
    }

    @Test
    @DisplayName("check if findAll works correct")
    void getAllActors() {
        //Data
        Actor actor1 = new Actor(1L, "Jan", "Nowacki", LocalDate.now(), null);
        Actor actor2 = new Actor(2L, "Adam", "Leszcz", LocalDate.now(), null);

        List<Actor> actorList = Arrays.asList(actor1, actor2);

        when(actorService.getAllActors()).thenReturn(actorList);

        assertEquals(actorList.size(), actorService.getAllActors().size());
    }

    @Test
    public void testAddActor() {
       ActorService mockActorService = mock(ActorService.class);
       Actor actor = new Actor();
       actor.setFirstName("Joanna");
       ActorDTO actorDTO = ActorMapper.INSTANCE.actorToDTO(actor);

       when(mockActorService.createActor(actorDTO)).thenReturn(actorDTO);

       ActorDTO result = mockActorService.createActor(actorDTO);

       assertEquals(result.getFirstName(), actorDTO.getFirstName());

    }
}