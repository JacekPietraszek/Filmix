package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.ActorNotFoundException;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;
import pl.wasko.filmixbackend.service.ActorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Long id) {
        return actorService
                .getActorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createActor(@Valid @RequestBody ActorDTO actorDTO) {
        ActorDTO savedActor = actorService.createActor(actorDTO);
        return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        try {
            actorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ActorNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addActors(@RequestBody List<ActorDTO> actorsDTO) {
        for (ActorDTO actorDTO : actorsDTO) {
            actorService.createActor(actorDTO);
        }
        return ResponseEntity.ok(actorsDTO);
    }


}
