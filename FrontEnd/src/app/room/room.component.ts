import { Component, Input } from '@angular/core';
import {NgClass} from '@angular/common';
import {RoomType} from '../interfaces/room-type';
import {SearchResult} from '../interfaces/SearchResult';



@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css'],

})
export class RoomComponent {
  @Input() roomData!: SearchResult;
}
