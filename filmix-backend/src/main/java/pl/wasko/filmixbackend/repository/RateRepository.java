package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Rate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    @Override
    List<Rate> findAll();

    @Override
    Optional<Rate> findById(Long id);

    List<Rate> findRatesByRating(Integer rating);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);
}
