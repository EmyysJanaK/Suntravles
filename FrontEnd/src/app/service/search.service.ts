import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Hotel} from '../interfaces/hotel';
import {Observable} from 'rxjs';
import {Criteria} from '../interfaces/criteria';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private apiUrl = 'http://localhost:8080/api/search'; // Adjust this URL based on your server endpoint

  constructor(private http: HttpClient) { }
  // Function to GET data to the Frontend
  getRooms(criteria: Criteria): Observable<any> {
    return this.http.post<any>(this.apiUrl, criteria);
  }
}

