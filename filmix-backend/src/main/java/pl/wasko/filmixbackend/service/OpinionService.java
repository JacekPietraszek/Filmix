package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.model.Opinion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OpinionService {
    OpinionDTO createOpinion(OpinionDTO opinionDTO);
    List<OpinionDTO> findAll();
    Optional<Opinion> findById(Long id);
    List<Opinion> findOpinionsByCreatedAt(LocalDateTime createdAt);
    boolean existsById(Long id);
    void deleteById(Long id);
    <S extends Opinion> S save(S entity);


}
