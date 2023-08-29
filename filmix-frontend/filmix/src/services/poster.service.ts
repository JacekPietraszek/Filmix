import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Image } from "src/app/models/video-content.model";


@Injectable({
    providedIn: 'root'
  })

  export class ImagesService {
    private apiUrl = ''; 

    constructor(private http: HttpClient) {}
    
    getImages() {
        return this.http.get<Image[]>(this.apiUrl);
      }

  }
