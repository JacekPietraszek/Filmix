import { Component, OnInit } from '@angular/core';
import { News } from '../models/news.model';
import { VideoContent } from '../models/video-content.model';
import { NewsService } from 'src/services/news.service';
import { FilmsService } from 'src/services/films.service';

@Component({
  selector: 'app-news-page',
  templateUrl: './news-page.component.html',
  styleUrls: ['./news-page.component.css']
})
export class NewsPageComponent implements OnInit {
  newsList: News[] = [];
  movieList: VideoContent[] = [];

  constructor(private newsServ: NewsService, private filmServ: FilmsService) {}

  ngOnInit(): void {
    this.newsServ.getNews().subscribe((newsData: News[]) => {
      this.newsList = newsData;
      this.fetchMovieList(); //fetch the list of movies
    });
  }

  private fetchMovieList(): void {
    this.filmServ.getFilms().subscribe((movies: VideoContent[]) => {
      this.movieList = movies;

      // match the movies to the news (movie id ===id in news model)
      this.newsList = this.newsList.map(news => {
        const correspondingMovie = this.movieList[news.movie.id];

        // If a corresponding movie is not found, leave the news without a movie.
        return correspondingMovie ? { ...news, movie: correspondingMovie } : news;
      });
    });
  }
}
