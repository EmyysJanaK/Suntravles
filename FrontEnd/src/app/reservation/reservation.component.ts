
import {Component, inject} from '@angular/core';
import {SearchComponent} from '../search/search.component';
import {RoomComponent} from '../room/room.component';
import {NgFor, NgIf} from '@angular/common';
import {Criteria} from '../interfaces/criteria';
import {SearchService} from '../service/search.service';
import {RoomType} from '../interfaces/room-type';
import {SearchResult} from '../interfaces/SearchResult';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  imports: [
    SearchComponent,
    RoomComponent,
    NgIf,
    NgFor
  ],
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent {
  searchService = inject(SearchService);
  data: SearchResult[] = [];
  filteredResults: SearchResult[]= []

  handleSearch(query: Criteria) {

    this.searchService.getRooms(query).subscribe({
      next: (response: SearchResult[]) => {
        this.data = response;
        this.filteredResults = [...this.data];
        console.log('Hotels loaded successfully:', response);
      },
      error: (error) => {
        console.error('Error occurred while loading hotels:', error);
        alert('Unable to load hotel data. Please try again later.');
      }
    });

  }
}
