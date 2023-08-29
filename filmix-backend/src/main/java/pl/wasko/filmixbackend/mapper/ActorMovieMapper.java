package pl.wasko.filmixbackend.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wasko.filmixbackend.exception.ActorNotFoundException;
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.ActorMovie;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;
import pl.wasko.filmixbackend.model.DTO.ActorMovieDTO;
import pl.wasko.filmixbackend.service.ActorService;

@Mapper(componentModel = "spring", uses = {ActorService.class})
public interface ActorMovieMapper {

    ActorMovieMapper INSTANCE = Mappers.getMapper(ActorMovieMapper.class);
    @Mapping(target = "actorId", source = "actor.id")
    ActorMovieDTO actorMovieToDTO(ActorMovie actorMovie);

    @Mapping(target = "actor.id", source = "actorId")
    ActorMovie dtoToActorMovie(ActorMovieDTO dto);



}
