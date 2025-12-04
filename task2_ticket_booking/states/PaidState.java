package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public class PaidState implements RequestState {

    @Override
    public void sendToClient(BookingRequest request) {
        System.out.println("Ошибка: Заявка уже оплачена.");
    }

    @Override
    public void processPayment(BookingRequest request, double amount) {
        System.out.println("Заявка уже оплачена.");
    }

    @Override
    public void paymentTimeout(BookingRequest request) {
        System.out.println("Ошибка: Заявка уже оплачена, таймаут неприменим.");
    }

    @Override
    public void verifyBooking(BookingRequest request) {
        boolean isVerified = performVerification(request);
        
        if (isVerified) {
            request.setState(new ConfirmedState());
            System.out.println("Бронирование подтверждено. Заявка #" + request.getRequestId() + " успешно завершена.");
        } else {
            System.out.println("Ошибка верификации бронирования.");
        }
    }

    private boolean performVerification(BookingRequest request) {
        System.out.println("Проверка бронирования для заявки #" + request.getRequestId() + "...");
        System.out.println("Проверка наличия мест...");
        System.out.println("Проверка данных клиента...");
        return true;
    }

    @Override
    public String getStateName() {
        return "Paid";
    }
}
