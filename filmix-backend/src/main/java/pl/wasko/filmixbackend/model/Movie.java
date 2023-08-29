package pl.wasko.filmixbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id;

    @Size(min = 2, max = 50)
    private String title;

    private LocalDate releaseDate;

    private String description;

    @NotNull
    private int duration;

    private int episodes;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Rate> rates;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<News> news;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<Category> categories;

    @OneToMany(mappedBy = "movie")
    private List<ActorMovie> actorMovieList;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, description, duration, episodes);
    }

}
