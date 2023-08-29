package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.NewsDTO;
import pl.wasko.filmixbackend.model.News;

@Mapper(componentModel = "spring")
public interface AutoNewsMapper {
    AutoNewsMapper MAPPER = Mappers.getMapper(AutoNewsMapper.class);

    NewsDTO mapToNewsDTO(News news);

    News mapToNews(NewsDTO newsDTO);

}
