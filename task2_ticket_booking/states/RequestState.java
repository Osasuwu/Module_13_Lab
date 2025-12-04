package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public interface RequestState {
    void sendToClient(BookingRequest request);
    void processPayment(BookingRequest request, double amount);
    void paymentTimeout(BookingRequest request);
    void verifyBooking(BookingRequest request);
    String getStateName();
}
