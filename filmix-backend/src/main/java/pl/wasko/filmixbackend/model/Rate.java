package pl.wasko.filmixbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rate")
@Data
@NoArgsConstructor
public class Rate {

    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Integer rating;

    public void updateFrom(final Rate source) {
        user = source.user;
        movie = source.movie;
        rating = source.rating;
    }
    public Rate(Long id, Integer rating) {
        this.id = id;
        this.rating = rating;
    }
}
