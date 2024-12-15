import {Component, inject, Input} from '@angular/core';
import {DeleteWarningPopupComponent} from '../delete-warning-popup-component/delete-warning-popup-component.component';
import {NgFor, NgIf} from '@angular/common';
import {ContractComponent} from '../contract/contract.component';
import {SubmitContractService} from '../service/submit-contract.service';
import {Hotel} from '../interfaces/hotel';
import {HotelService} from '../service/hotel.service';
import {HotelContract} from '../interfaces/hotel-contract';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  imports: [
    DeleteWarningPopupComponent,
    NgIf,
    NgFor,
    ContractComponent
  ],
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent {
  @Input() hotelData!: Hotel;
  isDeletePopupVisible = false; // To control the visibility of the popup
  contractService : SubmitContractService = inject(SubmitContractService);
  hotelService : HotelService = inject(HotelService);
  contracts: HotelContract[] = [];
  isViewingContracts: boolean = false; // Tracks whether contracts are being viewed

  onUpdate(hotel: Hotel) {
    console.log('Update hotel:', hotel);
  }
  toggleViewContracts() {
    this.isViewingContracts = !this.isViewingContracts;
  }
  onDeleteClick() {
    this.isDeletePopupVisible = true; // Show the delete warning popup
  }

  onDelete(hotelId: number | null) {
    console.log('Hotel deleted:', hotelId);
    this.isDeletePopupVisible = false; // Hide the popup after deletion
    // Add your API call here to delete the hotel
    if(hotelId!=null){
      this.hotelService.deleteById(hotelId).subscribe({
        next: (response: HotelContract[]) => {
          this.contracts = response;
          console.log('Hotel deleted successfully:', response);
        },
        error: (error: any) => {
          console.error('Error occurred while deleting hotels:', error);
          alert('Unable to delete hotel data. Please try again later.');
        },
      })
    }
  }

  onCancelDelete() {
    console.log('Delete cancelled');
    this.isDeletePopupVisible = false; // Hide the popup if cancelled
  }

  onViewContract(id: number | null) {
    this.toggleViewContracts();

    if (id != null){
      console.log('View contract for hotel:', id);
      this.contractService.getById(id).subscribe({
        next: (response: HotelContract[]) => {
          this.contracts = response;
          console.log('Hotels loaded successfully:', response);
        },
        error: (error) => {
          console.error('Error occurred while loading hotels:', error);
          alert('Unable to load hotel data. Please try again later.');
        },
      })

    }
  }
}
