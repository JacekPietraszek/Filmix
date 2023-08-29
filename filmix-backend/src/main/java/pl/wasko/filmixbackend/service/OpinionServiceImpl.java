package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.OpinionNotFoundException;
import pl.wasko.filmixbackend.mapper.AutoOpinionMapper;
import pl.wasko.filmixbackend.model.Opinion;
import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.repository.OpinionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;

    @Override
    public OpinionDTO createOpinion(OpinionDTO opinionDTO) {

        Opinion opinion = AutoOpinionMapper.MAPPER.mapToOpinion(opinionDTO);
        opinion.setCreatedAt(LocalDateTime.now());
        Opinion savedOpinion = opinionRepository.save(opinion);
        return AutoOpinionMapper.MAPPER.mapToOpinionDTO(savedOpinion);
    }

    @Override
    public List<OpinionDTO> findAll() {

        List<Opinion> opinions= opinionRepository.findAll();
        List<OpinionDTO> opinionsDTO = new ArrayList<>();

        for (Opinion opinion : opinions) {
            OpinionDTO dto = AutoOpinionMapper.MAPPER.mapToOpinionDTO(opinion);
            opinionsDTO.add(dto);
        }

        return Optional.of(opinionsDTO)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new OpinionNotFoundException("no opinions available"));
    }

    @Override
    public Optional<Opinion> findById(Long id) {

        return Optional.ofNullable(opinionRepository.findById(id)
                .orElseThrow(() -> new OpinionNotFoundException("opinion with the given id doesnt exist")));
    }

    @Override
    public List<Opinion> findOpinionsByCreatedAt(LocalDateTime createdAt) {

        List<Opinion> opinions = opinionRepository.findOpinionsByCreatedAt(createdAt);
        if (opinions.isEmpty()) {
            throw new OpinionNotFoundException("Opinions with the given createdAt not found");
        }
        return opinions;
    }

    @Override
    public boolean existsById(Long id) {

        return Optional.of(opinionRepository.existsById(id))
                .orElseThrow(() -> new OpinionNotFoundException("opinion with the given id doesnt exist"));
    }

    @Override
    public void deleteById(Long id) {

        opinionRepository.findById(id)
                .orElseThrow(() -> new OpinionNotFoundException("opinion with the given id doesn't exist"));

        opinionRepository.findById(id)
                .ifPresent(category -> opinionRepository.deleteById(id));
    }

    @Override
    public <S extends Opinion> S save(S entity) {

        S savedEntity = opinionRepository.save(entity);
        return Optional.ofNullable(savedEntity)
                .orElseThrow(() -> new OpinionNotFoundException("failed to save the opinion"));

    }
}
