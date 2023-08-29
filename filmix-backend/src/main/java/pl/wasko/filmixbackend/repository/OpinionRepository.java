package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Opinion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Override
    List<Opinion> findAll();

    @Override
    Optional<Opinion> findById(Long id);

    List<Opinion> findOpinionsByCreatedAt(LocalDateTime createdAt);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);
    @Override
    <S extends Opinion> S save(S entity);
}
