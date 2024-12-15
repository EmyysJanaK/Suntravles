import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RoomComponent } from './room.component';
import { By } from '@angular/platform-browser';

describe('RoomComponent', () => {
  let component: RoomComponent;
  let fixture: ComponentFixture<RoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[RoomComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(RoomComponent);
    component = fixture.componentInstance;
  });

  it('should display hotel name', () => {
    // Arrange
    component.roomData = {
      hotelName: 'Grand Hotel',
      roomType: 'Deluxe',
      price: 200,
      availableRooms: 5,
      contractStartDate: "2024-11-12",
      contractEndDate: "2024-12-12"
    };

    // Act
    fixture.detectChanges();
    const hotelNameElement = fixture.debugElement.query(By.css('h3')).nativeElement;

    // Assert
    expect(hotelNameElement.textContent).toContain('Grand Hotel');
  });

  it('should display room type', () => {
    // Arrange
    component.roomData = {
      hotelName: 'Grand Hotel',
      roomType: 'Deluxe',
      price: 200,
      availableRooms: 5,
      contractStartDate: "2024-11-12",
      contractEndDate: "2024-12-12"
    };

    // Act
    fixture.detectChanges();
    const roomTypeElement = fixture.debugElement.query(By.css('#RoomType')).nativeElement;

    // Assert
    expect(roomTypeElement.textContent).toContain('Deluxe');
  });

  it('should display room price', () => {
    // Arrange
    component.roomData = {
      hotelName: 'Grand Hotel',
      roomType: 'Deluxe',
      price: 200,
      availableRooms: 5,
      contractStartDate: "2024-11-12",
      contractEndDate: "2024-12-12"
    };

    // Act
    fixture.detectChanges();
    const priceElement = fixture.debugElement.query(By.css('#Price')).nativeElement;

    // Assert
    expect(priceElement.textContent).toContain('$200 per night');
  });

  it('should display available rooms', () => {
    // Arrange
    component.roomData = {
      hotelName: 'Grand Hotel',
      roomType: 'Deluxe',
      price: 200,
      availableRooms: 5,
      contractStartDate: "2024-11-12",
      contractEndDate: "2024-12-12"
    };

    // Act
    fixture.detectChanges();
    const availableRoomsElement = fixture.debugElement.query(By.css('#AvailableRooms')).nativeElement;

    // Assert
    expect(availableRoomsElement.textContent).toContain('5');
  });
});
