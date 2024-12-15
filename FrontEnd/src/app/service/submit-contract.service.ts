import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HotelContract} from '../interfaces/hotel-contract';

@Injectable({
  providedIn: 'root'
})
export class SubmitContractService {
  private apiUrl = 'http://localhost:8080/api/contracts'; // Adjust this URL based on your server endpoint

  constructor(private http: HttpClient) { }

// Function to POST data to the server
  postHotelContract(hotelContract: HotelContract): Observable<any> {
    return this.http.post<any>(this.apiUrl, hotelContract);
  }
  getById(hotelId: number): Observable<any> {
    return this.http.get<any>(this.apiUrl + '/hotel/' + hotelId);
  }
  deleteById(contractsId: number): Observable<any> {
    return this.http.delete<any>(this.apiUrl + '/'+ contractsId);
  }
}
