package pl.wasko.filmixbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO mapToRoleDto(Role role);

    Role mapToRole(RoleDTO roleDto);


}
