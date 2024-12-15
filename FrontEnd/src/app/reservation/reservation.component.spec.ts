import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReservationComponent } from './reservation.component';
import { SearchComponent } from '../search/search.component';
import { RoomComponent } from '../room/room.component';
import { of, throwError } from 'rxjs';
import { By } from '@angular/platform-browser';
import { SearchService } from '../service/search.service';
import { Room } from '../interfaces/SearchResult';
import { Criteria } from '../interfaces/criteria';
import { DebugElement } from '@angular/core';

describe('ReservationComponent', () => {
  let component: ReservationComponent;
  let fixture: ComponentFixture<ReservationComponent>;
  let searchServiceMock: jasmine.SpyObj<SearchService>;

  beforeEach(async () => {
    searchServiceMock = jasmine.createSpyObj('SearchService', ['getRooms']);

    await TestBed.configureTestingModule({
      imports: [ReservationComponent, SearchComponent, RoomComponent],
      providers: [{ provide: SearchService, useValue: searchServiceMock }],
    }).compileComponents();

    fixture = TestBed.createComponent(ReservationComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should display "No results found" if filteredResults is empty', () => {
    // Arrange
    component.filteredResults = [];

    // Act
    fixture.detectChanges();
    const noResultsElement = fixture.debugElement.query(By.css('.text-center p'));

    // Assert
    expect(noResultsElement).toBeTruthy();
    expect(noResultsElement.nativeElement.textContent).toContain('No results found.');
  });

  it('should render app-room components for each filtered result', () => {
    // Arrange
    const mockRooms: Room[] = [
      {
        hotelName: 'Hotel A',
        roomType: 'Deluxe',
        price: 150,
        availableRooms: 3 ,
        contractStartDate: "2024-10-12",
        contractEndDate: "2024-12-12",
      },
      {
        hotelName: 'Hotel B',
        roomType: 'Standard',
        price: 100,
        availableRooms: 5 ,
        contractStartDate: "2024-11-12",
        contractEndDate: "2024-12-12",
      },
    ];
    component.filteredResults = mockRooms;

    // Act
    fixture.detectChanges();
    const roomComponents = fixture.debugElement.queryAll(By.directive(RoomComponent));

    // Assert
    expect(roomComponents.length).toBe(2);
    expect(roomComponents[0].componentInstance.roomData).toEqual(mockRooms[0]);
    expect(roomComponents[1].componentInstance.roomData).toEqual(mockRooms[1]);
  });

  it('should handle search and update filteredResults when SearchComponent emits a query', () => {
    // Arrange
    const mockCriteria: Criteria = { checkInDate: '2023-12-25', nights: 3, rooms: [{ noOfRooms: 1, adults: 2 }] };
    const mockRooms: Room[] = [
      {
        hotelName: 'Hotel C',
        roomType: 'Suite',
        price: 200,
        availableRooms: 2 ,
        contractStartDate: "2024-11-12",
        contractEndDate: "2024-12-12",
      },
    ];
    searchServiceMock.getRooms.and.returnValue(of(mockRooms));

    // Act
    component.handleSearch(mockCriteria);

    // Assert
    expect(searchServiceMock.getRooms).toHaveBeenCalledWith(mockCriteria);
    expect(component.filteredResults).toEqual(mockRooms);
  });

  it('should display an alert and log an error if search fails', () => {
    // Arrange
    const mockCriteria: Criteria = { checkInDate: '2023-12-25', nights: 3, rooms: [{ noOfRooms: 1, adults: 2 }] };
    const error = new Error('Network error');
    spyOn(window, 'alert');
    searchServiceMock.getRooms.and.returnValue(throwError(() => error));

    // Act
    component.handleSearch(mockCriteria);

    // Assert
    expect(searchServiceMock.getRooms).toHaveBeenCalledWith(mockCriteria);
    expect(window.alert).toHaveBeenCalledWith('Unable to load hotel data. Please try again later.');
  });
});
