package pl.wasko.filmixbackend.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "opinion")
@Data
@AllArgsConstructor
public class Opinion {
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

    private LocalDateTime createdAt;

    private String opinion;
    public void updateFrom(final Opinion source) {
        user = source.user;
        movie = source.movie;
        createdAt = source.createdAt;
        opinion = source.opinion;
    }
    public Opinion() {
        this.createdAt = LocalDateTime.now();
    }

    public Opinion(Long id, String opinion) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.opinion = opinion;
    }

    public Opinion(Long id, LocalDateTime createdAt, String opinion) {
        this.id = id;
        this.createdAt = createdAt;
        this.opinion = opinion;
    }

}
