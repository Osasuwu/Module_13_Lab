package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public class CancelledState implements RequestState {

    @Override
    public void sendToClient(BookingRequest request) {
        System.out.println("Ошибка: Заявка отменена. Создайте новую заявку.");
    }

    @Override
    public void processPayment(BookingRequest request, double amount) {
        System.out.println("Ошибка: Заявка отменена. Создайте новую заявку.");
    }

    @Override
    public void paymentTimeout(BookingRequest request) {
        System.out.println("Заявка уже отменена.");
    }

    @Override
    public void verifyBooking(BookingRequest request) {
        System.out.println("Ошибка: Заявка отменена. Создайте новую заявку.");
    }

    @Override
    public String getStateName() {
        return "Cancelled";
    }
}
