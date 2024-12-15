import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RouterTestingModule } from '@angular/router/testing';
import {Router, RouterOutlet} from '@angular/router';
import { By } from '@angular/platform-browser';
import { AddHotelContractComponent } from './add-hotel-contract/add-hotel-contract.component';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { ReservationComponent } from './reservation/reservation.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {HotelService} from './service/hotel.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,AppComponent, NavbarComponent, FooterComponent, AddHotelContractComponent, AddHotelComponent, ReservationComponent
        ,RouterTestingModule.withRoutes([
        { path: '', component: AddHotelContractComponent },
        { path: 'hotel', component: AddHotelComponent },
        { path: 'search', component: ReservationComponent },
      ])]
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create the app component', () => {
    expect(component).toBeTruthy();
  });

  it('should contain the navbar component', () => {
    const navbarElement = fixture.debugElement.query(By.directive(NavbarComponent));
    expect(navbarElement).toBeTruthy(); // Ensures navbar is rendered
  });

  it('should contain the footer component', () => {
    const footerElement = fixture.debugElement.query(By.directive(FooterComponent));
    expect(footerElement).toBeTruthy(); // Ensures footer is rendered
  });

  it('should contain the router-outlet', () => {
    const routerOutlet = fixture.debugElement.query(By.directive(RouterOutlet));
    expect(routerOutlet).toBeTruthy(); // Ensures router-outlet is in the template
  });

  it('should navigate to AddHotelContractComponent when the default route is used', async () => {
    await router.navigate(['']);
    fixture.detectChanges();
    const hotelContractComponent = fixture.debugElement.query(By.directive(AddHotelContractComponent));
    expect(hotelContractComponent).toBeTruthy(); // Ensure AddHotelContractComponent is loaded for the default route
  });

  it('should navigate to AddHotelComponent when navigating to "/hotel"', async () => {
    await router.navigate(['hotel']);
    fixture.detectChanges();
    const addHotelComponent = fixture.debugElement.query(By.directive(AddHotelComponent));
    expect(addHotelComponent).toBeTruthy(); // Ensure AddHotelComponent is loaded when navigating to '/hotel'
  });

  it('should navigate to ReservationComponent when navigating to "/search"', async () => {
    await router.navigate(['search']);
    fixture.detectChanges();
    const reservationComponent = fixture.debugElement.query(By.directive(ReservationComponent));
    expect(reservationComponent).toBeTruthy(); // Ensure ReservationComponent is loaded when navigating to '/search'
  });

  it('should have a title "suntravels"', () => {
    expect(component.title).toBe('suntravels');
  });

  it('should render the navbar and footer correctly', () => {
    const navbar = fixture.debugElement.query(By.css('app-navbar'));
    const footer = fixture.debugElement.query(By.css('app-footer'));
    expect(navbar).toBeTruthy();  // Navbar should be present
    expect(footer).toBeTruthy();  // Footer should be present
  });

  it('should render the router outlet content when navigating', () => {
    const routerOutlet = fixture.debugElement.query(By.directive(RouterOutlet));
    expect(routerOutlet).toBeTruthy();  // Ensure router outlet is available for route display
  });
});
