package pl.wasko.filmixbackend.service;

import java.util.List;
import pl.wasko.filmixbackend.model.DTO.UserDTO;
import pl.wasko.filmixbackend.model.User;

public interface UserService {
    User addUser(UserDTO user);
    User getUserById(Long userId);
    void deleteById(Long userId);
    List<User> getAllUsers();

}
