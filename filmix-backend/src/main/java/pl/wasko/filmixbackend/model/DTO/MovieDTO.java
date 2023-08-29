package pl.wasko.filmixbackend.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.filmixbackend.model.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title;

    private LocalDate releaseDate;

    private String description;

    private int duration;

    private int episodes;

    private List<Rate> rates;

    private List<Opinion> opinions;

    private List<News> news;

    private Category category;

    private Image image;

    private List<Category> movieCategories;

    private List<ActorMovieDTO> actorMovieDtoList;
}

