import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { News } from "src/app/models/news.model";


@Injectable({
    providedIn: 'root'
  })

  export class NewsService {
    private apiUrl = 'https://filmix-80ef5-default-rtdb.firebaseio.com/news.json'; 

    constructor(private http: HttpClient) {}
    
    getNews() {
        return this.http.get<News[]>(this.apiUrl);
      }

  }
