package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.MovieDTO;
import pl.wasko.filmixbackend.model.Movie;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
    @Mapping(source = "actorMovieList", target = "actorMovieDtoList")
    MovieDTO movieToDTO(Movie movie);
    @Mapping(source = "actorMovieDtoList", target = "actorMovieList")
    Movie dtoToMovie(MovieDTO dto);

}
