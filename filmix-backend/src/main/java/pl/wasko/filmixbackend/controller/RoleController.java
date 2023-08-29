package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.RoleNotFoundException;
import pl.wasko.filmixbackend.mapper.RoleMapper;
import pl.wasko.filmixbackend.model.Role;
import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private RoleMapper roleMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    // Operate a POST request to create a new role

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<>(roleService.addRole(roleDTO), HttpStatus.CREATED);
    }

    // Operate the GET request for the specified role with the specified roleId
    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        return roleService
                .getRoleById(roleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Operate GET request for downloading all roles
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Operate DELETE request for deletion of role with specified roleId
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            roleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RoleNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoles(@RequestBody List<RoleDTO> rolesDTO) {
        for (RoleDTO roleDTO : rolesDTO) {
            roleService.addRole(roleDTO);
        }
        return ResponseEntity.ok(rolesDTO);
    }
}

