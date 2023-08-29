//package pl.wasko.filmixbackend.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.wasko.filmixbackend.model.Category;
//import pl.wasko.filmixbackend.model.DTO.CategoryDTO;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class CategoryServiceImplTest {
//
//    @Test
//    @DisplayName("create new news DTO")
//    void createNews_should_create_new_Category_DTO() {
//        // Given
//        CategoryService mockCategoryService = mock(CategoryService.class);
//        CategoryDTO dto = new CategoryDTO("testName",null,null);
//
//        // When
//        when(mockCategoryService.createCategory(dto)).thenReturn(dto);
//
//        CategoryDTO result = mockCategoryService.createCategory(dto);
//
//        // Then
//        assertEquals(result.getName(),dto.getName());
//        assertSame(dto,result);
//    }
//
//    @Test
//    @DisplayName("should return all categories from the database")
//    void findAll_should_return_all_categories() {
//        // Given
//        CategoryService mockCategoryService = mock(CategoryService.class);
//
//       List<Category> categoryList = Arrays.asList(
//               new Category(1L,"test1", null,null),
//               new Category(2L,"test2",null,null)
//       );
//        // When
//        when(mockCategoryService.findAll()).thenReturn(categoryList);
//
//        List<Category> result = mockCategoryService.findAll();
//
//        // Then
//        assertTrue(result.containsAll(categoryList));
//        assertEquals(2,result.size());
//    }
//
//    @Test
//    @DisplayName("find category by id")
//    void findById_shouldFindCategoryById() {
//        // Given
//        CategoryService mockCategoryService = mock(CategoryService.class);
//        List<Category> categoryList = Arrays.asList(
//                new Category(1L,"test1", null,null),
//                new Category(2L,"test2",null,null)
//        );
//        // When
//        when(mockCategoryService.findById(1L)).thenAnswer(inv -> {
//            Long id = inv.getArgument(0);
//            return categoryList.stream()
//                    .filter(news -> Objects.equals(news.getId(), id))
//                    .findFirst();
//        });
//
//        Optional<Category> result = mockCategoryService.findById(1L);
//
//        // Then
//        assertTrue(result.isPresent());
//        assertEquals("test1", result.get().getName());
//    }
//
//    @Test
//    @DisplayName("returns category with the same value in name")
//    void findCategoryByName_return_category_with_same_name() {
//        // Given
//        String expectedName = "Category 1";
//        Category expectedCategory = new Category(1L, expectedName);
//
//        CategoryService mockCategoryService = mock(CategoryService.class);
//
//        // When
//        when(mockCategoryService.findCategoriesByName(expectedName)).thenReturn(Optional.of(expectedCategory));
//        Optional<Category> result = mockCategoryService.findCategoriesByName(expectedName);
//
//        // Then
//        assertTrue(result.isPresent());
//        assertEquals(expectedCategory, result.get());
//    }
//
//    @Test
//    @DisplayName("checks if there is a category with the given id")
//    void existsById_return_boolean() {
//        // Given
//        List<Category> categories = Arrays.asList(
//                new Category(1L, "Category 1"),
//                new Category(2L, "Category 2"),
//                new Category(3L, "Category 3")
//        );
//        CategoryService mockCategoryService = mock(CategoryService.class);
//
//        Long id = 2L;
//
//        // When
//        when(mockCategoryService.existsById(id)).thenReturn(true);
//
//        boolean tmp = categories.stream()
//                .anyMatch(category -> Objects.equals(category.getId(), id));
//        boolean result =  mockCategoryService.existsById(id);
//        // Then
//        assertEquals(tmp, result);
//        assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("should delete Category by id")
//    void deleteCategoryById() {
//        // Given
//        List<Category> categories = new ArrayList<>();
//        categories.add(new Category(1L, "Category 1"));
//        categories.add(new Category(2L, "Category 2"));
//        categories.add(new Category(3L, "Category 3"));
//
//        CategoryService mockCategoryService = mock(CategoryService.class);
//        doAnswer(invocation -> {
//            Long id = invocation.getArgument(0);
//            categories.removeIf(category -> Objects.equals(category.getId(), id));
//            return null;
//        }).when(mockCategoryService).deleteById(anyLong());
//
//        // When
//        mockCategoryService.deleteById(2L);
//
//        // Then
//        assertEquals(2L, categories.size());
//        assertFalse(categories.stream().anyMatch(category -> category.getId() == 2L));
//    }
//
//    @Test
//    @DisplayName("saving new Category object")
//    void save() {
//        // Given
//        CategoryService mockCategoryService = mock(CategoryService.class);
//        Category category = new Category(1L,"Test", null, null);
//
//        // When
//        when(mockCategoryService.save(category)).thenReturn(category);
//
//        Category result = mockCategoryService.save(category);
//
//        //Then
//        assertSame(result, category);
//    }
//}