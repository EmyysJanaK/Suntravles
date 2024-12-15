import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
  imports: [
    RouterLink
  ],
  // Or you can use `styles` directly in the component
})
export class FooterComponent {
  currentYear: number = new Date().getFullYear();
}
