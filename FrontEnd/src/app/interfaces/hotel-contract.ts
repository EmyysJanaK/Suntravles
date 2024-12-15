import {RoomType} from './room-type';

export interface HotelContract {
  hotelId: number,
  startDate: string,
  endDate: string ,
  defaultMarkup: number,
  rooms: RoomType[]
}
