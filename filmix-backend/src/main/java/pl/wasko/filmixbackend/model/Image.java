package pl.wasko.filmixbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;


    @OneToOne(mappedBy = "image")
    private Movie movie;
}
