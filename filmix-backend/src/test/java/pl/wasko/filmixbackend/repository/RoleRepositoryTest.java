package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.repository.RoleRepository;
import pl.wasko.filmixbackend.model.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
@SpringBootTest
public class RoleRepositoryTest {

    @Mock
    private RoleRepository roleRepository;
    public RoleRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }


    @BeforeEach
    public void setup() {

        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setName("ROLE_USER");

    }

    @Test
    public void testFindAll() {
        List<Role> roles = roleRepository.findAll();
        Assertions.assertEquals(0, roles.size());
    }

    @Test
    public void testFindById() {
        Role role1 = new Role();
        role1.setName("Kamil");
        role1.setId(1L);
        RoleRepository mockRoleRepository = mock(RoleRepository.class);
        when(mockRoleRepository.findById(1)).thenReturn(Optional.of(role1));

        assertEquals("Kamil", mockRoleRepository.findById(1).map(Role::getName));
        Assertions.assertNotNull(mockRoleRepository.findById(1));

    }
    @Test
    public void testFindByRole() {
        Role role2 = new Role();
        role2.setName("admin");
        role2.setId(1L);

        RoleRepository mockRoleRepository = mock(RoleRepository.class);
        when(mockRoleRepository.findByName("admin")).thenReturn(Optional.of(role2));

        assertEquals("admin", mockRoleRepository.findByName("admin").map(Role::getName));
        Assertions.assertNotNull(mockRoleRepository.findByName("admin"));
    }


}
