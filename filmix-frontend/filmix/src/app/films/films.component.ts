import { Component, OnInit } from '@angular/core';
import { FilmsService } from '../../services/films.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MovieDetailsComponent } from '../tv-series-details/movie-details.component';
import {VideoContent} from '../models/video-content.model';
import { OrganizingVideos } from '../models/video.content';
import { BehaviorSubject } from 'rxjs';
import {ImagesService} from '../../services/poster.service';
import { Image } from '../models/video-content.model';


@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css'],
})
export class FilmsComponent implements OnInit {

    
  constructor(private organazing: OrganizingVideos, private images: ImagesService) {}

  filmList: VideoContent[] = [];

  ngOnInit(): void {
    console.log('FilmsComponent ngOnInit called');
    //this.filmList = this.organazing.getMovies();
    this.organazing.getMovies().subscribe((movieData: VideoContent[]) => {
      this.filmList = movieData;
      console.log(this.filmList);
      
    
  });
 

//posters from api
 this.images.getImages().subscribe((imageData: Image[]) => {
  for (const video of this.filmList) {
    const matchingImage = imageData.find((image) => image.id === video.image.id);
    console.log(matchingImage)
    if (matchingImage) {
      video.image.imageUrl = matchingImage.imageUrl;
    }
  }
  console.log(imageData);
});
}
}

