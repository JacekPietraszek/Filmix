import { Component, Injectable, OnInit } from '@angular/core';
import { FilmsService } from '../../services/films.service';
import { ActivatedRoute } from '@angular/router';
import {VideoContent} from '../models/video-content.model';
import { Actor } from '../models/video-content.model';
import { ActorMovie } from '../models/video-content.model';
import { BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  
export class OrganizingVideos implements OnInit {
    
    

  //public serialData: VideoContent[] = [];
  //public movieData: VideoContent[] = [];
  private movieData: BehaviorSubject<VideoContent[]> = new BehaviorSubject<VideoContent[]>([]);
  private serialData: BehaviorSubject<VideoContent[]> = new BehaviorSubject<VideoContent[]>([]);
 

  constructor(
    private route: ActivatedRoute,
     private filmsService: FilmsService,
     
  ) {  this.getVideoData();}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const videoTitle = params['title']; 
      const videoDesc= params['description'];
      const duration = params['duration'];
      const releaseDate= params['releaseDate'];
      this.getVideoData();
    });
  }

  getVideoData(): void {
    console.log('getVideoData start');
    this.filmsService.getFilms().subscribe( data => {

      const assignedType= this.filmsService.assignType(data);
      this.movieData.next(assignedType.filter(video => video.type === 'film'));
      this.serialData.next(assignedType.filter(video => video.type === 'serial'));
      //this.movieData = assignedType.filter(video => video.type === 'film');
      //this.serialData= assignedType.filter(video => video.type==='serial');
      console.log(this.movieData);
      console.log(this.serialData);
      
    },
    error => {
      console.error('Error fetching films:', error);
    }


  );
  console.log('getVideoData ends');  
  }
 // getMovies(): VideoContent[] {
  //  return this.movieData;
 // }

 // getSerials(): VideoContent[] {
   // return this.serialData;
    // Metoda do dostępu do obiektu BehaviorSubject jako Observable
  getMovies(): Observable<VideoContent[]> {
    return this.movieData.asObservable();
  }

  getSerials(): Observable<VideoContent[]> {
    return this.serialData.asObservable();
  }
  }
  

 
  







  
