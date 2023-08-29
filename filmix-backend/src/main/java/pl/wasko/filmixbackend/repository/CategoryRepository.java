package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Category;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Long id);

    Optional<Category> findCategoriesByName(String name);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    <S extends Category> S save(S entity);
}
