import { Component } from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {AddHotelContractComponent} from './add-hotel-contract/add-hotel-contract.component';
import {HttpClient} from '@angular/common/http';
import {NavbarComponent} from './navbar/navbar.component';
import {FooterComponent} from './footer/footer.component';

@Component({
  selector: 'app-root',
  imports: [RouterModule, NavbarComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'suntravels';
}
