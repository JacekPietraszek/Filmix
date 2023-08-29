package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.Actor;
import pl.wasko.filmixbackend.model.DTO.ActorDTO;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    ActorDTO actorToDTO(Actor actor);

    Actor dtoToActor(ActorDTO dto);
}
