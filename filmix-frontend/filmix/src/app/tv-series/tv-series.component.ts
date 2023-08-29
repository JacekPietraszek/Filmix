import { Component, OnInit } from '@angular/core';
import { FilmsService } from '../../services/films.service';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import VideoContent from '../models/video-content.model';
import { OrganizingVideos } from '../models/video.content';
@Component({
  selector: 'app-tv-series',
  templateUrl: './tv-series.component.html',
  styleUrls: ['./tv-series.component.css']
})
export class TvSeriesComponent implements OnInit {

   /* tablica filmow pobrana z api*/
   serialList: VideoContent[] = [

   
  
    
   ];
   constructor(private organazing: OrganizingVideos) {}

   
 
   ngOnInit(): void {
     console.log('FilmsComponent ngOnInit called');
     //this.filmList = this.organazing.getMovies();
     this.organazing.getSerials().subscribe((serialData: VideoContent[]) => {
       this.serialList = serialData;
       console.log(this.serialList);
     
   });
  
 
  // getMovieDataSubject() {
     
  // }
 }
 }


