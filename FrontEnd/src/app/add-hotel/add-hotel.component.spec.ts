import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddHotelComponent } from './add-hotel.component';
import { HotelService } from '../service/hotel.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';

describe('AddHotelComponent', () => {
  let component: AddHotelComponent;
  let fixture: ComponentFixture<AddHotelComponent>;
  let mockHotelService: jasmine.SpyObj<HotelService>;

  beforeEach(async () => {
    // Create a spy object for HotelService
    mockHotelService = jasmine.createSpyObj('HotelService', ['postHotel']);

    await TestBed.configureTestingModule({
      imports: [AddHotelComponent, HttpClientTestingModule, FormsModule],
      providers: [{ provide: HotelService, useValue: mockHotelService }],
    }).compileComponents();

    fixture = TestBed.createComponent(AddHotelComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should submit hotel data successfully', () => {
    const mockResponse = { message: 'Hotel added successfully!' };
    mockHotelService.postHotel.and.returnValue(of(mockResponse));
    component.hotelData = { hotelName: 'Test Hotel', location: 'Test Location' };

    spyOn(window, 'alert'); // Spy on the alert function
    component.handleSubmit();

    expect(mockHotelService.postHotel).toHaveBeenCalledWith(component.hotelData);
    expect(window.alert).toHaveBeenCalledWith(
      'Hotel Data Submitted:\nName: Test Hotel\nLocation: Test Location'
    );
    expect(window.alert).toHaveBeenCalledWith('Form submission successful!');
  });

  it('should handle error during hotel data submission', () => {
    const mockError = { message: 'Submission failed!' };
    mockHotelService.postHotel.and.returnValue(throwError(mockError));
    component.hotelData = { hotelName: 'Test Hotel', location: 'Test Location' };

    spyOn(window, 'alert'); // Spy on the alert function
    component.handleSubmit();

    expect(mockHotelService.postHotel).toHaveBeenCalledWith(component.hotelData);
    expect(window.alert).toHaveBeenCalledWith('Form submission failed!');
  });
});
