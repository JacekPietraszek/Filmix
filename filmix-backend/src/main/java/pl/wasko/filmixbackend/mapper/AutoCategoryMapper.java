package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.Category;
import pl.wasko.filmixbackend.model.DTO.CategoryDTO;

@Mapper(componentModel = "spring")
public interface AutoCategoryMapper {

AutoCategoryMapper MAPPER = Mappers.getMapper(AutoCategoryMapper.class);

CategoryDTO mapToCategoryDTO(Category category);

Category mapToCategory(CategoryDTO categoryDTO);
}
