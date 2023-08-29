package pl.wasko.filmixbackend.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.Opinion;
import pl.wasko.filmixbackend.model.Rate;
import pl.wasko.filmixbackend.model.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String password;

    private String email;

}