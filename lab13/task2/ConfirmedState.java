package com.designpatterns.lab13.task2;

/**
 * Состояние: Confirmed - Подтверждена
 */
public class ConfirmedState implements IBookingRequestState {
    @Override
    public void sendToClient(BookingRequestContext context) {
        System.out.println("ℹ️ Бронирование уже подтверждено.");
    }

    @Override
    public void makePayment(BookingRequestContext context) {
        System.out.println("ℹ️ Оплата уже получена.");
    }

    @Override
    public void confirmBooking(BookingRequestContext context) {
        System.out.println("ℹ️ Бронирование уже подтверждено.");
    }

    @Override
    public void cancelRequest(BookingRequestContext context) {
        System.out.println("❌ Невозможно отменить подтвержденное бронирование.");
        System.out.println("   Обратитесь к администратору.");
    }

    @Override
    public String getStateName() {
        return "Подтверждена (Confirmed)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Бронирование успешно завершено!");
        System.out.println("   Билет подтвержден и готов к использованию.");
    }
}
