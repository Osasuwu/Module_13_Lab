package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public class CreatedState implements RequestState {

    @Override
    public void sendToClient(BookingRequest request) {
        request.setState(new AwaitingPaymentState());
        System.out.println("Заявка #" + request.getRequestId() + " отправлена клиенту " + request.getClientName() + ".");
        System.out.println("Ожидается оплата в течение " + request.getPaymentTimeoutMinutes() + " минут.");
    }

    @Override
    public void processPayment(BookingRequest request, double amount) {
        System.out.println("Ошибка: Заявка еще не отправлена клиенту.");
    }

    @Override
    public void paymentTimeout(BookingRequest request) {
        System.out.println("Ошибка: Заявка еще не отправлена клиенту.");
    }

    @Override
    public void verifyBooking(BookingRequest request) {
        System.out.println("Ошибка: Заявка еще не оплачена.");
    }

    @Override
    public String getStateName() {
        return "Created";
    }
}
