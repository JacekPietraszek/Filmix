package pl.wasko.filmixbackend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wasko.filmixbackend.mapper.UserMapper;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.User;
import pl.wasko.filmixbackend.repository.UserRepository;
import pl.wasko.filmixbackend.service.UserService;
import pl.wasko.filmixbackend.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserMapper userMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        UserService mockUserService = mock(UserService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Kamil123");
        userDTO.setPassword("Qwerty123");

        User user = UserMapper.INSTANCE.mapToUser(userDTO);
        when(mockUserService.addUser(userDTO)).thenReturn(user);

        User result = mockUserService.addUser(userDTO);

        assertEquals("Kamil123", result.getUsername());
        assertEquals("Qwerty123", result.getPassword());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Kamil123");
        user.setPassword("Qwerty123");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(1L);

        assertEquals("Kamil123", retrievedUser.getUsername());
        assertEquals("Qwerty123", retrievedUser.getPassword());
        assertEquals(1L, retrievedUser.getId());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("Kamil123");
        user1.setPassword("Qwerty123");

        User user2 = new User();
        user2.setUsername("Marek69");
        user2.setPassword("Elo420");

        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> retrievedUsers = userService.getAllUsers();

        assertEquals(2, retrievedUsers.size());
        assertEquals("Kamil123", retrievedUsers.get(0).getUsername());
        assertEquals("Qwerty123", retrievedUsers.get(0).getPassword());
        assertEquals("Marek69", retrievedUsers.get(1).getUsername());
        assertEquals("Elo420", retrievedUsers.get(1).getPassword());
    }
}
