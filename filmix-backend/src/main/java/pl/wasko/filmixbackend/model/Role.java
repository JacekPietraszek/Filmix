package pl.wasko.filmixbackend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> userList;


}
