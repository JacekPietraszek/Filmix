package pl.wasko.filmixbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.filmixbackend.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List <User> findAll();

    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void deleteById(Long integer);
}