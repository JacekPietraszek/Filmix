package pl.wasko.filmixbackend.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override
    List<Role> findAll(Sort sort);

    @Override
    Optional<Role> findById(Integer integer);

    Optional<Role> findById(Long id);

    Optional<Role> findByName (String username);
    void deleteById(Long id);
}
