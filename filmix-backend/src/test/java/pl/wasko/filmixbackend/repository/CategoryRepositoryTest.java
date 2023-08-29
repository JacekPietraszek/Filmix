package pl.wasko.filmixbackend.repository;

import pl.wasko.filmixbackend.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Test
    @DisplayName("should return all categories from the database")
    void findAll_should_return_all_categories() {
        // Given
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1"),
                new Category(2L, "Category 2"),
                new Category(3L, "Category 3")
        );

        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
        when(mockCategoryRepository.findAll()).thenReturn(categories);

        // When
        List<Category> result = mockCategoryRepository.findAll();

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsAll(categories));
    }


    @Test
    @DisplayName("find category by id")
    void findById_shouldFindCategoryById() {
        // Given
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1"),
                new Category(2L, "Category 2"),
                new Category(3L, "Category 3")
        );
        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);

        // When
        when(mockCategoryRepository.findById(anyLong())).thenAnswer(inv -> {
            Long id = inv.getArgument(0);
            return categories.stream()
                    .filter(category -> Objects.equals(category.getId(), id))
                    .findFirst();
        });

        Optional<Category> result = mockCategoryRepository.findById(2L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Category 2", result.get().getName());
    }

    @Test
    @DisplayName("returns category with the same value in name")
    void findCategoryByName_return_category_with_same_name() {
        // Given
        String expectedName = "Category 1";
        Category expectedCategory = new Category(1L, expectedName);

        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);

        // When
        when(mockCategoryRepository.findCategoriesByName(expectedName)).thenReturn(Optional.of(expectedCategory));
        Optional<Category> result = mockCategoryRepository.findCategoriesByName(expectedName);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedCategory, result.get());
    }


    @Test
    @DisplayName("checks if there is a category with the given id")
    void existsById_return_boolean() {
        // Given
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1"),
                new Category(2L, "Category 2"),
                new Category(3L, "Category 3")
        );
        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);

        Long id = 2L;

        // When
        when(mockCategoryRepository.existsById(id)).thenReturn(true);

        boolean tmp = categories.stream()
                .anyMatch(category -> Objects.equals(category.getId(), id));
        boolean result =  mockCategoryRepository.existsById(id);
        // Then
        assertEquals(tmp, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("should delete Category by id")
    void deleteCategoryById() {
        // Given
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1"));
        categories.add(new Category(2L, "Category 2"));
        categories.add(new Category(3L, "Category 3"));

        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            categories.removeIf(category -> Objects.equals(category.getId(), id));
            return null;
        }).when(mockCategoryRepository).deleteById(anyLong());

        // When
        mockCategoryRepository.deleteById(2L);

        // Then
        assertEquals(2, categories.size());
        assertFalse(categories.stream().anyMatch(category -> category.getId() == 2));
    }
}
