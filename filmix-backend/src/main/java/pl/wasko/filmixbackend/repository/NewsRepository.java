package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.News;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    List<News> findAll();

    @Override
    Optional<News> findById(Long integer);

    Optional<News> findNewsByCreatedAt(LocalDateTime createdAt);

    @Override
    boolean existsById(Long integer);

    @Override
    void deleteById(Long integer);

    @Override
    <S extends News> S save(S entity);
}
