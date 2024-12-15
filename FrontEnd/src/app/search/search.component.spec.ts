import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SearchComponent } from './search.component';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Criteria } from '../interfaces/criteria';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchComponent, FormsModule],
    }).compileComponents();

    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should add a new room when addRoom() is called', () => {
    const initialRoomCount = component.reservationCriteria.rooms.length;
    component.addRoom();
    expect(component.reservationCriteria.rooms.length).toBe(initialRoomCount + 1);
  });

  it('should remove a room when removeRoom() is called', () => {
    component.addRoom(); // Ensure at least one extra room is added
    const initialRoomCount = component.reservationCriteria.rooms.length;
    component.removeRoom(0);
    expect(component.reservationCriteria.rooms.length).toBe(initialRoomCount - 1);
  });

  it('should emit reservation criteria when submit() is called', () => {
    spyOn(component.search, 'emit');
    const mockCriteria: Criteria = {
      checkInDate: '2024-01-01',
      nights: 2,
      rooms: [{ noOfRooms: 1, adults: 2 }],
    };

    component.reservationCriteria = mockCriteria;
    component.submit();

    expect(component.search.emit).toHaveBeenCalledWith(mockCriteria);
  });

  it('should render the correct number of room fields', () => {
    component.addRoom();
    fixture.detectChanges(); // Trigger template updates
    const roomFields = fixture.debugElement.queryAll(By.css('div.flex.items-center.gap-4'));
    expect(roomFields.length).toBe(component.reservationCriteria.rooms.length);
  });

  it('should call addRoom() when the Add Room button is clicked', () => {
    spyOn(component, 'addRoom');
    const button = fixture.debugElement.query(By.css('#AddRoom'));

    button.nativeElement.click();
    expect(component.addRoom).toHaveBeenCalled();
  });

  it('should call removeRoom() when a Remove button is clicked', () => {
    component.addRoom(); // Ensure at least one room is present
    fixture.detectChanges();
    spyOn(component, 'removeRoom');
    const removeButton = fixture.debugElement.query(By.css('button.bg-red-500'));
    removeButton.nativeElement.click();
    expect(component.removeRoom).toHaveBeenCalledWith(0); // Remove the first room
  });

  it('should call submit() when the Submit button is clicked', () => {
    spyOn(component, 'submit');
    const submitButton = fixture.debugElement.query(By.css('button.bg-green-500'));
    submitButton.nativeElement.click();
    expect(component.submit).toHaveBeenCalled();
  });
});
