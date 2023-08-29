package pl.wasko.filmixbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wasko.filmixbackend.exception.UserNotFoundException;
import pl.wasko.filmixbackend.mapper.UserMapper;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.Role;
import pl.wasko.filmixbackend.repository.UserRepository;
import pl.wasko.filmixbackend.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserDTO userDTO) {
        // Business logic adding a user;
        User user = userMapper.mapToUser(userDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Optional<Role> userRole = roleService.getRoleById(1L);
        userRole.ifPresent(user::setRole);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long roleId) {
        // Business logic retrieving the role by Id;
        return userRepository.findById(roleId).orElseThrow( () -> new UserNotFoundException("Role with ID " + roleId
                + " not found"));
        // When a Role with Id is not found it shows the message role with id
    }

    @Override
    public List<User> getAllUsers() {
        // Business logic downloading all users;
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteById(Long userId) {
        // Business logic deleting a user by Id;
        userRepository.deleteById(userId);
    }

}