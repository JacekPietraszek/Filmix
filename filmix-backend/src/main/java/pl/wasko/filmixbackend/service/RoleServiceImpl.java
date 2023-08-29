package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.mapper.RoleMapper;
import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.repository.RoleRepository;
import pl.wasko.filmixbackend.model.Role;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        //business logic adding a new role;
        Role role = RoleMapper.INSTANCE.mapToRole(roleDTO);
        Role roleToSave = roleRepository.save(role);
        return RoleMapper.INSTANCE.mapToRoleDto(roleToSave);
    }

    @SneakyThrows
    @Override
    public Optional<Role> getRoleById(Long roleId) {
        // Business logic retrieving the role by Id;
        return roleRepository.findById(roleId);
        // When a Role with Id is not found it shows the message role with id
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        roleRepository
                .findById(id)
                .ifPresentOrElse(
                        actor -> roleRepository.deleteById(id),
                        () -> {
                            try {
                                throw new RoleNotFoundException("role with the given id doesn't exist");
                            } catch (RoleNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }


    @Override
    public List<RoleDTO> getAllRoles() {
        // Business logic downloads all roles;
        List<Role> roleList = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role: roleList) {
            RoleDTO roleDTO = RoleMapper.INSTANCE.mapToRoleDto(role);
            roleDTOList.add(roleDTO);
        }

        return roleDTOList;
    }


}
