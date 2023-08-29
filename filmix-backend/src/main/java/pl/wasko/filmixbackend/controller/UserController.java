package pl.wasko.filmixbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.filmixbackend.exception.UserNotFoundException;
import pl.wasko.filmixbackend.mapper.UserMapper;
import pl.wasko.filmixbackend.model.User;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private UserMapper userMapper;

    // Operate a POST request to create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.CREATED);
    }

    // Operate the GET request for the specified user with the specified userId
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Operate GET request for downloading all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOList = users.stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    // Operate DELETE request for deletion of user with specified userId
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUsers(@RequestBody List<UserDTO> usersDTO) {
        for (UserDTO userDTO : usersDTO) {
            userService.addUser(userDTO);
        }
        return ResponseEntity.ok(usersDTO);
    }
}