package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.CategoryNotFoundException;
import pl.wasko.filmixbackend.mapper.AutoCategoryMapper;
import pl.wasko.filmixbackend.model.Category;
import pl.wasko.filmixbackend.model.DTO.CategoryDTO;
import pl.wasko.filmixbackend.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = AutoCategoryMapper.MAPPER.mapToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return AutoCategoryMapper.MAPPER.mapToCategoryDTO(savedCategory);
    }

    @Override
    public List<Category> findAll() {

        return Optional.of(categoryRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CategoryNotFoundException("no categories available"));

    }

    @Override
    public Optional<Category> findById(Long id) {

        return Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("category with the given id doesnt exist")));
    }

    @Override
    public Optional<Category> findCategoriesByName(String name) {

        return Optional.ofNullable(categoryRepository.findCategoriesByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("category with the given name doesnt exist")));
    }

    @Override
    public boolean existsById(Long id) {

        return Optional.of(categoryRepository.existsById(id))
                .orElseThrow(() -> new CategoryNotFoundException("category with the given id doesnt exist"));
    }

    @Override
    public void deleteById(Long id) {

        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("category with the given id doesn't exist"));

        categoryRepository.findById(id)
                .ifPresent(category -> categoryRepository.deleteById(id));

    }

    @Override
    public <S extends Category> S save(S entity) {
        S savedEntity = categoryRepository.save(entity);
        return Optional.ofNullable(savedEntity)
                .orElseThrow(() -> new CategoryNotFoundException("Failed to save the category"));

    }
}
