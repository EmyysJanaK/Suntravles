import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddHotelContractComponent } from './add-hotel-contract.component';
import { SubmitContractService } from '../service/submit-contract.service';
import { HotelService } from '../service/hotel.service';
import { of, throwError } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import {importProvidersFrom} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

describe('AddHotelContractComponent', () => {
  let component: AddHotelContractComponent;
  let fixture: ComponentFixture<AddHotelContractComponent>;
  let mockSubmitContractService: jasmine.SpyObj<SubmitContractService>;
  let mockHotelService: jasmine.SpyObj<HotelService>;

  beforeEach(async () => {
    // Create spies for services
    mockSubmitContractService = jasmine.createSpyObj('SubmitContractService', ['postHotelContract']);
    mockHotelService = jasmine.createSpyObj('HotelService', ['getHotels']);

    // Configure test module
    await TestBed.configureTestingModule({
      imports: [AddHotelContractComponent, FormsModule, RouterTestingModule, HttpClientTestingModule],
      providers: [
        { provide: SubmitContractService, useValue: mockSubmitContractService },
        { provide: HotelService, useValue: mockHotelService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AddHotelContractComponent);
    component = fixture.componentInstance;
  });


  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should load hotels on initialization', () => {
    const mockHotels = [
      { hotelId: 1, hotelName: 'Hotel A', location : 'lacation A' },
      { hotelId: 2, hotelName: 'Hotel B', location : 'lacation B' },
    ];
    mockHotelService.getHotels.and.returnValue(of(mockHotels));

    component.ngOnInit();

    expect(mockHotelService.getHotels).toHaveBeenCalled();
    expect(component.hotels).toEqual(mockHotels);
  });

  it('should handle error when loading hotels', () => {
    spyOn(window, 'alert');
    mockHotelService.getHotels.and.returnValue(throwError(() => new Error('API Error')));

    component.ngOnInit();

    expect(mockHotelService.getHotels).toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith('Unable to load hotel data. Please try again later.');
  });

  it('should add a new room type', () => {
    const initialLength = component.hotelContract.roomTypes.length;
    component.addRoomType();

    expect(component.hotelContract.roomTypes.length).toBe(initialLength + 1);
    expect(component.hotelContract.roomTypes[initialLength]).toEqual({
      roomTypeName: '',
      baseRate: 0,
      maxAdults: 0,
      totalRooms: 0,
    });
  });

  it('should reset the form', () => {
    component.hotelContract = {
      hotelId: 123,
      startDate: '2024-01-01',
      endDate: '2024-12-31',
      defaultMarkup: 10,
      roomTypes: [{ roomTypeName: 'Deluxe', baseRate: 200, maxAdults: 2, totalRooms: 5 }],
    };

    component.handleReset();

    expect(component.hotelContract).toEqual({
      hotelId: 0,
      startDate: '',
      endDate: '',
      defaultMarkup: 0,
      roomTypes: [{ roomTypeName: '', baseRate: 0, maxAdults: 0, totalRooms: 0 }],
    });
  });

  it('should submit the form', () => {
    spyOn(window, 'alert');
    const formData = {
      hotelId: 123,
      startDate: '2024-01-01',
      endDate: '2024-12-31',
      rooms: [{ roomTypeName: 'Suite', baseRate: 300, maxAdults: 3, totalRooms: 10 }],
      defaultMarkup: 15,
    };
    mockSubmitContractService.postHotelContract.and.returnValue(of({ success: true }));

    component.hotelContract = {
      hotelId: 123,
      startDate: '2024-01-01',
      endDate: '2024-12-31',
      defaultMarkup: 15,
      roomTypes: [{ roomTypeName: 'Suite', baseRate: 300, maxAdults: 3, totalRooms: 10 }],
    };

    component.handleSubmit();

    expect(mockSubmitContractService.postHotelContract).toHaveBeenCalledWith(formData);
    expect(window.alert).toHaveBeenCalledWith('Form submission successful!');
  });

  it('should handle form submission error', () => {
    spyOn(window, 'alert');
    mockSubmitContractService.postHotelContract.and.returnValue(throwError(() => new Error('Submission Failed')));

    component.handleSubmit();

    expect(mockSubmitContractService.postHotelContract).toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalledWith('Form submission failed!');
  });
});
