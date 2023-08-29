package pl.wasko.filmixbackend.service;

import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDTO addRole(RoleDTO roleDTO);
    Optional<Role> getRoleById(Long roleId);
    void deleteById(long roleId);
    List<RoleDTO> getAllRoles();
}