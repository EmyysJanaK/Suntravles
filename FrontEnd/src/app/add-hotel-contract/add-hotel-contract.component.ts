import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HotelContract} from '../interfaces/hotel-contract';
import {RoomType} from '../interfaces/room-type';
import {SubmitContractService} from '../service/submit-contract.service';
import {HotelService} from '../service/hotel.service';
import {NgFor} from '@angular/common';
import {RouterLink} from '@angular/router';
import {Hotel} from '../interfaces/hotel';

@Component({
  selector: 'app-add-hotel-contract',
  imports: [
    FormsModule, NgFor, RouterLink
  ],
  templateUrl: './add-hotel-contract.component.html',
  styleUrl: './add-hotel-contract.component.css'
})
export class AddHotelContractComponent {
  // Inject services
  private contractService: SubmitContractService = inject(SubmitContractService);
  private hotelService: HotelService = inject(HotelService);

  // Hotels data array
  hotels: Hotel[] = [];

  // Set up hotel contract data
  hotelContract: HotelContract = {
    hotelId: 0,
    startDate: '',
    endDate: '',
    defaultMarkup: 0,
    rooms: [{ roomTypeId : null, contractId : null ,roomTypeName: '', baseRate: 0, maxAdults: 0, totalRooms: 0 }],
  };

  tempRoomType: RoomType = { roomTypeId : null, contractId : null ,roomTypeName: '', baseRate: 0, maxAdults: 0, totalRooms: 0 };

  constructor() {}

  ngOnInit(): void {
    // Call service to fetch hotel data when component loads
    this.loadHotels();
  }

  loadHotels() {
    this.hotelService.getHotels().subscribe({
      next: (response: Hotel[]) => {
        this.hotels = response;
        console.log('Hotels loaded successfully:', response);
      },
      error: (error) => {
        console.error('Error occurred while loading hotels:', error);
        alert('Unable to load hotel data. Please try again later.');
      },
    });
  }

  // Add a new row for room type
  addRoomType() {
    this.hotelContract.rooms.push(this.tempRoomType);
    this.tempRoomType = { roomTypeId : null, contractId : null ,roomTypeName: '', baseRate: 0, maxAdults: 0, totalRooms: 0 };

  }

  // Handle the reset button
  handleReset() {
    this.hotelContract.hotelId = 0;
    this.hotelContract.startDate = '';
    this.hotelContract.endDate = '';
    this.hotelContract.defaultMarkup = 0;
    this.hotelContract.rooms = [{ roomTypeId : null, contractId : null ,roomTypeName: '', baseRate: 0, maxAdults: 0, totalRooms: 0 }];
  }

  // Handle the form submission
  handleSubmit() {
    const formData:HotelContract = {
      hotelId: this.hotelContract.hotelId,
      startDate: this.hotelContract.startDate,
      endDate: this.hotelContract.endDate,
      rooms: this.hotelContract.rooms,
      defaultMarkup: this.hotelContract.defaultMarkup,
    };

    console.log('Form Submitted:', formData);
    this.contractService.postHotelContract(formData).subscribe({
      next: (response) => {
        console.log('Form Submitted successfully:', response);
        alert('Form submission successful!');
      },
      error: (error) => {
        console.error('Error occurred while submitting the form:', error);
        alert('Form submission failed!');
      }
    });
    this.handleReset();
  }

}

