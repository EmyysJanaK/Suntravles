import {RoomCriteria} from './room-criteria';

export interface Criteria {
  checkInDate: string; // Format: YYYY-MM-DD
  nights: number;
  rooms: RoomCriteria[];
}
