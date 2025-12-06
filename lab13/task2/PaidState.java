package com.designpatterns.lab13.task2;

/**
 * Состояние: Paid - Оплачена
 */
public class PaidState implements IBookingRequestState {
    @Override
    public void sendToClient(BookingRequestContext context) {
        System.out.println("ℹ️ Заявка уже оплачена.");
    }

    @Override
    public void makePayment(BookingRequestContext context) {
        System.out.println("ℹ️ Заявка уже оплачена.");
    }

    @Override
    public void confirmBooking(BookingRequestContext context) {
        System.out.println("✅ Бронирование подтверждено!");
        System.out.println("🎫 Билет для " + context.getClientName() + " успешно забронирован");
        System.out.println("🆔 Номер бронирования: " + context.getRequestId());
        context.addToHistory("Бронирование подтверждено системой");
        context.setState(new ConfirmedState());
    }

    @Override
    public void cancelRequest(BookingRequestContext context) {
        System.out.println("⚠️ Заявка уже оплачена.");
        System.out.println("   Для отмены необходимо обратиться к администратору для возврата средств.");
    }

    @Override
    public String getStateName() {
        return "Оплачена (Paid)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Подтвердить бронирование (ConfirmBooking)");
    }
}
