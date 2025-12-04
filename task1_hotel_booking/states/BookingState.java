package task1_hotel_booking.states;

import task1_hotel_booking.context.BookingContext;

public interface BookingState {
    void selectRoom(BookingContext context, String roomNumber);
    void changeRoom(BookingContext context, String newRoomNumber);
    void confirmBooking(BookingContext context);
    void pay(BookingContext context, double amount, double discount);
    void cancel(BookingContext context);
    String getStateName();
}
