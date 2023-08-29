import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from 'src/app/models/categories.model';


@Injectable({
    providedIn: 'root'
  })

  export class CategoryService {
    private apiUrl = 'https://filmix-80ef5-default-rtdb.firebaseio.com/category.json'; 

    constructor(private http: HttpClient) {}
    
    getCategories() {
        return this.http.get<Category[]>(this.apiUrl);
      }

  }