package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO mapToUserDto(User user);

    User mapToUser(UserDTO userDto);

}
