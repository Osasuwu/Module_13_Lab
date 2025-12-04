package task2_ticket_booking.states;

import task2_ticket_booking.context.BookingRequest;

public class AwaitingPaymentState implements RequestState {

    @Override
    public void sendToClient(BookingRequest request) {
        System.out.println("Заявка уже отправлена клиенту.");
    }

    @Override
    public void processPayment(BookingRequest request, double amount) {
        double requiredAmount = request.getTicketPrice();
        
        if (amount >= requiredAmount) {
            request.setAmountPaid(amount);
            request.setState(new PaidState());
            System.out.println("Оплата принята. Сумма: " + amount + ". Заявка #" + request.getRequestId());
        } else {
            System.out.println("Ошибка: Недостаточная сумма. Требуется: " + requiredAmount + ", получено: " + amount);
        }
    }

    @Override
    public void paymentTimeout(BookingRequest request) {
        request.setState(new CancelledState());
        System.out.println("Время оплаты истекло. Заявка #" + request.getRequestId() + " отменена.");
    }

    @Override
    public void verifyBooking(BookingRequest request) {
        System.out.println("Ошибка: Заявка еще не оплачена.");
    }

    @Override
    public String getStateName() {
        return "AwaitingPayment";
    }
}
