
import {HotelComponent} from '../hotel/hotel.component';
import {Component, inject} from '@angular/core';
import {HotelService} from '../service/hotel.service';
import {NgForOf} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {Hotel} from '../interfaces/hotel';


@Component({
  selector: 'app-update-hotels-ncontracts',
  imports: [
    HotelComponent,
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './update-hotels-ncontracts.component.html',
  styleUrl: './update-hotels-ncontracts.component.css'
})
export class UpdateHotelsNcontractsComponent {

  // Inject services
  private hotelService: HotelService = inject(HotelService);

  // Hotels data array
  hotels: Hotel[] = [];



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


}

