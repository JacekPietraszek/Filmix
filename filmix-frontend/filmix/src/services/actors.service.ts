import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Actor } from 'src/app/models/video-content.model';



@Injectable({
    providedIn: 'root'
  })

  export class ActorsService {
    private apiUrl = 'https://filmix-80ef5-default-rtdb.firebaseio.com/actors.json'; 

    constructor(private http: HttpClient) {}
    
    getActors() {
        return this.http.get<Actor[]>(this.apiUrl);
      }

      

  }