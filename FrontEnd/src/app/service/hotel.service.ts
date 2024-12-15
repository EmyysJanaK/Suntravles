import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Hotel} from '../interfaces/hotel';

@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private apiUrl = 'http://localhost:8080/api/hotels'; // Adjust this URL based on your server endpoint

  constructor(private http: HttpClient) { }

  // Function to POST data to the server
  postHotel(hotel: Hotel): Observable<any> {
    return this.http.post<any>(this.apiUrl, hotel);
  }
  // Function to GET data to the Frontend
  getHotels(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
  deleteById(hotelId: number): Observable<any> {
    return this.http.delete(this.apiUrl + "/" +hotelId);
  }
}
