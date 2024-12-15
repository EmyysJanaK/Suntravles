import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Hotel} from '../interfaces/hotel';
import {HotelService} from '../service/hotel.service';

@Component({
  selector: 'app-add-hotel',
  imports: [
    FormsModule
  ],
  templateUrl: './add-hotel.component.html',
  styleUrl: './add-hotel.component.css'
})
export class AddHotelComponent {
  hotelData:Hotel = {
    hotelId: null,
    hotelName: '',
    location: '',
  };
  HotelService: HotelService = inject(HotelService);

  handleSubmit() {
    console.log('Form Submitted:', this.hotelData);
    alert(`Hotel Data Submitted:\nName: ${this.hotelData.hotelName}\nLocation: ${this.hotelData.location}`);
    this.HotelService.postHotel(this.hotelData).subscribe({
      next: (response) => {
        console.log('Form Submitted successfully:', response);
        alert('Form submission successful!');
      },
      error: (error) => {
        console.error('Error occurred while submitting the form:', error);
        alert('Form submission failed!');
      }
    });
  }
}
