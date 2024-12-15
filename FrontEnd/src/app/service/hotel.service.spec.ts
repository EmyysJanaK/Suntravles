import { TestBed } from '@angular/core/testing';
import { HotelService } from './hotel.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Hotel } from '../interfaces/hotel';
import { of } from 'rxjs';

describe('HotelService', () => {
  let service: HotelService;
  let httpMock: HttpTestingController;

  const mockHotel: Hotel = {
    hotelName: 'Hotel Sunshine',
    location: 'Beach City'
  };

  const mockHotels: Hotel[] = [
    { hotelName: 'Hotel Sunshine', location: 'Beach City' },
    { hotelName: 'Mountain Resort', location: 'Mountain Town'},
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [HotelService],
    });

    service = TestBed.inject(HotelService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifies that no outstanding requests are left
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to add a hotel', () => {
    service.postHotel(mockHotel).subscribe(response => {
      expect(response).toEqual(mockHotel); // You can modify this depending on the actual expected response
    });

    const req = httpMock.expectOne('http://localhost:8080/api/hotels');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockHotel);
    req.flush(mockHotel); // Simulate a successful response
  });

  it('should send a GET request and return a list of hotels', () => {
    service.getHotels().subscribe(response => {
      expect(response).toEqual(mockHotels);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/hotels');
    expect(req.request.method).toBe('GET');
    req.flush(mockHotels); // Simulate a successful response with mock hotels
  });

  it('should handle GET request error', () => {
    const errorMessage = 'Unable to fetch hotels';

    service.getHotels().subscribe({
      next: () => fail('should have failed with an error'),
      error: (error) => {
        expect(error.status).toBe(500);
        expect(error.statusText).toBe('Internal Server Error');
        expect(error.error).toBe(errorMessage);
      },
    });

    const req = httpMock.expectOne('http://localhost:8080/api/hotels');
    expect(req.request.method).toBe('GET');
    req.flush(errorMessage, { status: 500, statusText: 'Internal Server Error' });
  });
});
