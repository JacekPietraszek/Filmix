package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wasko.filmixbackend.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTest {


    @Mock
    private UserRepository userRepository;


    public UserRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void setup() {

        User user1 = new User();
        user1.setUsername("Kamil123");
        user1.setPassword("Qwerty123");

        User user2 = new User();
        user2.setUsername("Marek69");
        user2.setPassword("Elo420");

    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(0, users.size());
    }

    @Test
    public void testFindById() {
        User user1 = new User();
        user1.setUsername("Kamil123");
        user1.setPassword("Qwerty123");
        user1.setId(1L);
        UserRepository mockRoleRepository = mock(UserRepository.class);
        when(mockRoleRepository.findById(1)).thenReturn(Optional.of(user1));

        Optional<User> optionalUser = mockRoleRepository.findById(1);
        optionalUser.ifPresent(user -> assertEquals("Kamil123", user.getUsername()));

    }

    @Test
    public void testFindByUsername() {
        User user1 = new User();
        user1.setUsername("Kamil123");
        user1.setPassword("Qwerty123");
        user1.setId(1L);
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.findByUsername("Kamil123")).thenReturn(Optional.of(user1));

        Optional<User> optionalUser = mockUserRepository.findByUsername("Kamil123");
        optionalUser.ifPresent(user -> assertEquals("Qwerty123", user.getPassword()));

    }
}