package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.RateNotFoundException;
import pl.wasko.filmixbackend.mapper.AutoRateMapper;
import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;
import pl.wasko.filmixbackend.repository.RateRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    @Override
    public RateDTO createRate(RateDTO rateDTO) {

        Rate rate = AutoRateMapper.MAPPER.mapToRate(rateDTO);
        Rate savedRate = rateRepository.save(rate);

        return AutoRateMapper.MAPPER.mapToRateDTO(savedRate);
    }

    @Override
    public List<Rate> findAll() {

        return Optional.of(rateRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new RateNotFoundException("no rates available"));
    }

    @Override
    public Optional<Rate> findById(Long id) {

        return Optional.ofNullable(rateRepository.findById(id)
                .orElseThrow(() -> new RateNotFoundException("rate with the given id doesnt exist")));
    }
    @Override
    public List<Rate> findRatesByRating(Integer rating) {
        List<Rate> rates = rateRepository.findRatesByRating(rating);
        if (rates.isEmpty()) {
            throw new RateNotFoundException("Rates with the given rating do not exist");
        }
        return rates;
    }

    @Override
    public boolean existsById(Long id) {
        return Optional.of(rateRepository.existsById(id))
                .orElseThrow(() -> new RateNotFoundException("rate with the given id doesnt exist"));
    }

    @Override
    public void deleteById(Long id) {
        rateRepository.findById(id)
                .orElseThrow(() -> new RateNotFoundException("rate with the given id doesn't exist"));

        rateRepository.findById(id)
                .ifPresent(category -> rateRepository.deleteById(id));
    }

    @Override
    public <S extends Rate> S save(S entity) {
        S savedEntity = rateRepository.save(entity);
        return Optional.of(savedEntity)
                .orElseThrow(() -> new RateNotFoundException("failed to save the rate"));

    }
}
