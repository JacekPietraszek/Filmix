package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.OpinionDTO;
import pl.wasko.filmixbackend.model.Opinion;

@Mapper(componentModel = "spring")
public interface AutoOpinionMapper {
    AutoOpinionMapper MAPPER = Mappers.getMapper(AutoOpinionMapper.class);

    OpinionDTO mapToOpinionDTO(Opinion opinion);

    Opinion mapToOpinion(OpinionDTO opinionDTO);
}
