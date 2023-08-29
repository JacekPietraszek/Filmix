package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.CategoryNotFoundException;
import pl.wasko.filmixbackend.model.DTO.CategoryDTO;
import pl.wasko.filmixbackend.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }

    }
    @PostMapping("/add")
    public ResponseEntity<?> addCategories(@RequestBody List<CategoryDTO> categoriesDTO) {
        for (CategoryDTO categoryDTO : categoriesDTO) {
            categoryService.createCategory(categoryDTO);
        }
        return ResponseEntity.ok(categoriesDTO);
    }
}
