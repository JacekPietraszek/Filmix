package pl.wasko.filmixbackend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpinionDTO {
    private User user;
    private Movie movie;
    private LocalDateTime createdAt;
    private String opinion;

}
