package pl.wasko.filmixbackend.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
public class News {

    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDateTime createdAt;

    @NotBlank(message = "news content must not be empty")
    private String content;

    public News(Long id, String content) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.content = content;
    }

    public News() {
        this.createdAt = LocalDateTime.now();
    }

}
