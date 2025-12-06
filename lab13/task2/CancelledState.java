package com.designpatterns.lab13.task2;

/**
 * Состояние: Cancelled - Отменена (финальное состояние)
 */
public class CancelledState implements IBookingRequestState {
    @Override
    public void sendToClient(BookingRequestContext context) {
        System.out.println("❌ Невозможно отправить отмененную заявку.");
    }

    @Override
    public void makePayment(BookingRequestContext context) {
        System.out.println("❌ Невозможно оплатить отмененную заявку.");
    }

    @Override
    public void confirmBooking(BookingRequestContext context) {
        System.out.println("❌ Невозможно подтвердить отмененную заявку.");
    }

    @Override
    public void cancelRequest(BookingRequestContext context) {
        System.out.println("ℹ️ Заявка уже отменена.");
    }

    @Override
    public String getStateName() {
        return "Отменена (Cancelled) - Финальное состояние";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Заявка отменена.");
        System.out.println("   Создайте новую заявку для бронирования.");
    }
}
