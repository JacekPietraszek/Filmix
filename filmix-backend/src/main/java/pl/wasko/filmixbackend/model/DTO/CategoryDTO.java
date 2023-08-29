package pl.wasko.filmixbackend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.Movie;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryDTO {

    private String name;

    private List<Movie> movieCategoryList;

    private List<Movie> movieList;


}
