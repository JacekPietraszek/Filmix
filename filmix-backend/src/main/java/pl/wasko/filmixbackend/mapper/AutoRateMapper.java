package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.RateDTO;
import pl.wasko.filmixbackend.model.Rate;

@Mapper(componentModel = "spring")
public interface AutoRateMapper {
    AutoRateMapper MAPPER = Mappers.getMapper(AutoRateMapper.class);

    RateDTO mapToRateDTO(Rate Rate);

    Rate mapToRate(RateDTO rateDTO);


}
