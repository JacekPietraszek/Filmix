package pl.wasko.filmixbackend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.Movie;
import pl.wasko.filmixbackend.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private User user;
    private Movie movie;
    private Integer rating;
}
