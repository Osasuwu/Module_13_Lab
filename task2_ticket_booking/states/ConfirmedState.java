package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public class ConfirmedState implements RequestState {

    @Override
    public void sendToClient(BookingRequest request) {
        System.out.println("Ошибка: Бронирование уже подтверждено.");
    }

    @Override
    public void processPayment(BookingRequest request, double amount) {
        System.out.println("Ошибка: Бронирование уже подтверждено и оплачено.");
    }

    @Override
    public void paymentTimeout(BookingRequest request) {
        System.out.println("Ошибка: Бронирование уже подтверждено.");
    }

    @Override
    public void verifyBooking(BookingRequest request) {
        System.out.println("Бронирование уже подтверждено.");
    }

    @Override
    public String getStateName() {
        return "Confirmed";
    }
}
