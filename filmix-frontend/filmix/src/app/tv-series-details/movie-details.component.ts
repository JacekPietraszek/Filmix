import { Component, OnInit } from '@angular/core';
import { FilmsService } from '../../services/films.service';
import { ActivatedRoute } from '@angular/router';
import { ActorsService } from '../../services/actors.service'; // Popraw ścieżkę importu
import { Actor, ActorMovie } from '../models/video-content.model'; // Popraw ścieżkę importu

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  movieData: any = {};

  constructor(
    private route: ActivatedRoute,
    private filmsService: FilmsService,
    private actorsService: ActorsService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const movieTitle = params['title'];
      this.getMovieData(movieTitle);
    });
  }

  getMovieData(movieTitle: string): void {
    this.filmsService.getFilms().subscribe(
      data => {
        const assignedType = this.filmsService.assignType(data);
        this.movieData = assignedType.find((movie: any) => movie.title === movieTitle);
        
        this.actorsService.getActors().subscribe((actors: Actor[]) => {
          console.log('Actors:', actors);
        
          this.movieData.actorMovieDtoList.forEach((actorMovie: ActorMovie) => {
            console.log('actorMovie:', actorMovie);
            const correspondingActor = actors[actorMovie.actorId];
          
            
            if (correspondingActor) {
              actorMovie.actor = correspondingActor;
              console.log('Assigned Actor:', actorMovie.actor);
            }
          });
        });
      },
      error => {
        console.error('Błąd podczas pobierania danych filmu:', error);
      }
    );
  }
}
