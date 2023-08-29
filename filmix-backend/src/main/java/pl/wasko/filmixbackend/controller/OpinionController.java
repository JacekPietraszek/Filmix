package pl.wasko.filmixbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.model.Opinion;
import pl.wasko.filmixbackend.service.OpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/opinions")
@RequiredArgsConstructor
public class OpinionController {

    private static final Logger logger = LoggerFactory.getLogger(OpinionController.class);
    @Autowired
    private final OpinionService opinionService;

    @GetMapping
    ResponseEntity<List<OpinionDTO>> getAll(){
        logger.warn("Exposing all the opinions");
        return ResponseEntity.ok(opinionService.findAll());
    }
    @GetMapping("/{id}")
    ResponseEntity<Optional<Opinion>> getOpinionById(@PathVariable Long id){
        logger.warn("Exposing opinion with id -> {}", id);
        return ResponseEntity.ok(opinionService.findById(id));
    }
    @GetMapping("createdAt/{createdAt}")
    ResponseEntity<List<Opinion>> getOpinionsByCreatedAt(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt){
        logger.warn("Exposing opinion by createdAt");
        return ResponseEntity.ok(opinionService.findOpinionsByCreatedAt(createdAt));
    }
    @PostMapping
    ResponseEntity<?> createOpinion(@Valid @RequestBody OpinionDTO opinionDTO){
        OpinionDTO createdOpinion = opinionService.createOpinion(opinionDTO);
        return ResponseEntity.ok(createdOpinion);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody @Valid Opinion toUpdate) {
        if (!opinionService.existsById(id))
            return ResponseEntity.notFound().build();

        opinionService.findById(id)
                .ifPresent(opinion -> {
                    opinion.updateFrom(toUpdate);
                    opinionService.save(opinion);
                });
        logger.warn("Opinion with id -> {} updated", id);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOpinion(@PathVariable Long id){
        opinionService.deleteById(id);
        logger.warn("Opinion with id -> {} deleted", id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/add")
    public ResponseEntity<?> addOpinions(@RequestBody List<OpinionDTO> opinionsDTO) {
        for (OpinionDTO opinionDTO : opinionsDTO) {
            opinionService.createOpinion(opinionDTO);
        }
        return ResponseEntity.ok(opinionsDTO);
    }
}
