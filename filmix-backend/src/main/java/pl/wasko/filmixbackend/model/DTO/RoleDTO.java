package pl.wasko.filmixbackend.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoleDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    private List<User> userList;
}
