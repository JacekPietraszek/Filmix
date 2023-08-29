package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.Category;
import pl.wasko.filmixbackend.model.DTO.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<Category> findAll();

    Optional<Category> findById(Long integer);

    Optional<Category> findCategoriesByName(String name);

    boolean existsById(Long integer);

    void deleteById(Long integer);

    <S extends Category> S save(S entity);

}
