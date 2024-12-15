import { TestBed } from '@angular/core/testing';
import { SubmitContractService } from './submit-contract.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HotelContractDTO, RoomTypeRequestDTO} from '../interfaces/hotel-contract-create';

describe('SubmitContractService', () => {
  let service: SubmitContractService;
  let httpMock: HttpTestingController;

  const mockHotelContract: HotelContractDTO = {

    hotelId: 4,
    startDate: '2024-01-01',
    endDate: '2024-12-31' ,
    defaultMarkup: 12,
    rooms:  [{
      roomTypeName: "Deluxe",
      maxAdults: 2,
      totalRooms: 1,
      baseRate: 6,
    }]
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SubmitContractService],
    });

    service = TestBed.inject(SubmitContractService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifies that no outstanding requests are left
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a POST request to submit a hotel contract', () => {
    service.postHotelContract(mockHotelContract).subscribe(response => {
      expect(response).toEqual({ message: 'Contract created successfully' }); // Expect a success response
    });

    const req = httpMock.expectOne('http://localhost:8080/api/contracts');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockHotelContract); // Ensure the request body contains the hotel contract data
    req.flush({ message: 'Contract created successfully' }); // Simulate a successful response
  });

  it('should handle POST request error', () => {
    const errorMessage = 'Unable to create contract';

    service.postHotelContract(mockHotelContract).subscribe({
      next: () => fail('should have failed with an error'),
      error: (error) => {
        expect(error.status).toBe(500);
        expect(error.statusText).toBe('Internal Server Error');
        expect(error.error).toBe(errorMessage);
      },
    });

    const req = httpMock.expectOne('http://localhost:8080/api/contracts');
    expect(req.request.method).toBe('POST');
    req.flush(errorMessage, { status: 500, statusText: 'Internal Server Error' });
  });
});
