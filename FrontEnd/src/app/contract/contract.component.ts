import { Component, Input } from '@angular/core';
import {NgFor} from '@angular/common';
import {RoomType} from '../interfaces/room-type';
import {HotelContract} from '../interfaces/hotel-contract'; // Ensure correct interfaces

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css'],
  imports:[NgFor]
})
export class ContractComponent {
  @Input() contractData!: HotelContract ;

  // Method to handle updating the contract
  updateContract(contractData: HotelContract): void {
    console.log('Updating contract:', contractData);
    // Add your logic here for updating the contract
  }

  // Method to handle deleting the contract
  deleteContract(hotelId: number): void {
    console.log('Deleting contract for hotel ID:', hotelId);
    // Add your logic here for deleting the contract
  }

  // Method to handle updating a room
  updateRoom(room: RoomType): void {
    console.log('Updating room:', room);
    // Add your logic here for updating the room
  }

  // Method to handle deleting a room
  deleteRoom(roomTypeId: number | null): void {
    if(roomTypeId!=null){
      console.log('Deleting room with ID:', roomTypeId);

    }
    else{
      console.log('Null Room Type ID');
    }
    // Add your logic here for deleting the room
  }
}
