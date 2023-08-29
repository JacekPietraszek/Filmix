package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;
import java.util.List;
import java.util.Optional;

public interface RateService {
    RateDTO createRate(RateDTO rateDTO);
    List<Rate> findAll();
    Optional<Rate> findById(Long integer);
    List<Rate> findRatesByRating(Integer rating);
    boolean existsById(Long integer);
    void deleteById(Long integer);
    <S extends Rate> S save(S entity);
}
