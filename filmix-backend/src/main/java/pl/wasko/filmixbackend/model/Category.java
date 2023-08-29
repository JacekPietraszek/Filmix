package pl.wasko.filmixbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    @JsonBackReference
    private Set<Movie> movieCategoryList;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Movie> movieList;

  public Category(Long id, String name) {
      this.id = id;
      this.name = name;
  }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
