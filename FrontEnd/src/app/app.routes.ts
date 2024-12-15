import { Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {AddHotelContractComponent} from './add-hotel-contract/add-hotel-contract.component';
import {AddHotelComponent} from './add-hotel/add-hotel.component';
import {ReservationComponent} from './reservation/reservation.component';
import {UpdateHotelsNcontractsComponent} from './update-hotels-ncontracts/update-hotels-ncontracts.component';

export const routes: Routes = [
  {
    path: '',
    component: AddHotelContractComponent,
  },
  {
    path: 'hotel',
    component: AddHotelComponent,
  },
  {
    path: 'search',
    component: ReservationComponent,
  },
  {
    path: 'view',
    component: UpdateHotelsNcontractsComponent,
  },

];
