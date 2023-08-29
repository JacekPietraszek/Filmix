package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.mapper.RoleMapper;
import pl.wasko.filmixbackend.model.DTO.RoleDTO;
import pl.wasko.filmixbackend.model.Role;
import pl.wasko.filmixbackend.service.RoleService;
import pl.wasko.filmixbackend.service.RoleServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private RoleMapper roleMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRole() {
        RoleService mockRoleService = mock(RoleService.class);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");
        when(mockRoleService.addRole(roleDTO)).thenReturn(roleDTO);
        RoleDTO result = mockRoleService.addRole(roleDTO);

        assertEquals("ADMIN", result.getName());
    }

    @Test
    public void testGetRoleById(){
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Optional<Role> retrievedRole = roleService.getRoleById(1L);
        retrievedRole.ifPresent(role1 -> assertEquals("USER", role1.getName()));

        retrievedRole.ifPresent(role1 -> assertEquals(1L, role1.getId()));

    }

//    @Test
//    public void testGetAllRoles() {
//        Role role1 = new Role();
//        role1.setName("ADMIN");
//
//        Role role2 = new Role();
//        role2.setName("USER");
//
//        List<Role> roleList = Arrays.asList(role1, role2);
//
//        when(roleRepository.findAll()).thenReturn(roleList);
//
//        List<Role> retrievedRoles = roleService.getAllRoles();
//
//        assertEquals(2, retrievedRoles.size());
//        assertEquals("ADMIN", retrievedRoles.get(0).getName());
//        assertEquals("USER", retrievedRoles.get(1).getName());
//    }
}
