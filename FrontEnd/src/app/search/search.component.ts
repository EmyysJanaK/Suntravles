import { Component, EventEmitter, Output } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';
import {Criteria} from '../interfaces/criteria';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  imports: [
    FormsModule,
    NgForOf
  ]
})
export class SearchComponent {
  @Output() search = new EventEmitter<Criteria>();


  reservationCriteria: Criteria = {
    checkInDate: '',
    nights: 1,
    rooms: [{ noOfRooms: 1, adults: 2 }],
  };

  addRoom() {
    this.reservationCriteria.rooms.push({noOfRooms: 1, adults: 1 });
  }

  removeRoom(index: number) {
    this.reservationCriteria.rooms.splice(index, 1);
  }

  submit() {
    this.search.emit(this.reservationCriteria);
  }
}
