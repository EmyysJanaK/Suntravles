import { TestBed } from '@angular/core/testing';
import { SearchService } from './search.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Criteria } from '../interfaces/criteria';
import { Room } from '../interfaces/SearchResult';
import {RoomCriteria} from '../interfaces/room-criteria';

describe('SearchService', () => {
  let service: SearchService;
  let httpMock: HttpTestingController;

  const mockCriteria: Criteria = {
    checkInDate: "2024-12-11",
    nights: 3,
    rooms: [{
      noOfRooms: 4,
      adults: 2
    }]
  };

  const mockRooms: Room[] = [
    {  hotelName: 'Hotel Sunshine', roomType: 'Deluxe', price: 200, availableRooms: 5, contractStartDate: "2024-10-12",
      contractEndDate: "2024-12-12", },
    {  hotelName: 'Beach Resort', roomType: 'Deluxe', price: 250, availableRooms: 3 , contractStartDate: "2024-10-12",
      contractEndDate: "2024-12-12", },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SearchService],
    });

    service = TestBed.inject(SearchService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifies that no outstanding requests are left
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to search for rooms', () => {
    service.getRooms(mockCriteria).subscribe(response => {
      expect(response).toEqual(mockRooms); // Check that the response matches the mockRooms data
    });

    const req = httpMock.expectOne('http://localhost:8080/api/search');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockCriteria); // Ensure the request body contains the correct search criteria
    req.flush(mockRooms); // Simulate a successful response with mock room data
  });

  it('should handle POST request error', () => {
    const errorMessage = 'Unable to fetch rooms';

    service.getRooms(mockCriteria).subscribe({
      next: () => fail('should have failed with an error'),
      error: (error) => {
        expect(error.status).toBe(500);
        expect(error.statusText).toBe('Internal Server Error');
        expect(error.error).toBe(errorMessage);
      },
    });

    const req = httpMock.expectOne('http://localhost:8080/api/search');
    expect(req.request.method).toBe('POST');
    req.flush(errorMessage, { status: 500, statusText: 'Internal Server Error' });
  });
});
