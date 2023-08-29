package pl.wasko.filmixbackend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.Movie;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private Movie movie;
    private LocalDateTime createdAt;
    private String content;

    public NewsDTO(Movie movie, String content) {
        this.movie = movie;
        this.createdAt = LocalDateTime.now();
        this.content = content;
    }

}
