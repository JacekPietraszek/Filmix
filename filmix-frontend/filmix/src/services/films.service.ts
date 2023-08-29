// serwis do obsługi żądań HTTP do API

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VideoContent } from '../app/models/video-content.model';





//

@Injectable({
  providedIn: 'root'
})
export class FilmsService {
  private apiUrl = 'https://filmix-80ef5-default-rtdb.firebaseio.com/g.json'; 
  

  constructor(private http: HttpClient) { }

  getFilms() {
    return this.http.get<VideoContent[]>(this.apiUrl);
  }


  public assignType(data: VideoContent[]): VideoContent[] {
    return data.map(item => ({
      ...item,
      type: item.episodes === 0 ? 'film' : 'serial'
    }));

}

}
