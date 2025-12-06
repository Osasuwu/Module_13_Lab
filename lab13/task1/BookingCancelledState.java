package com.designpatterns.lab13.task1;

/**
 * Состояние: BookingCancelled - бронирование отменено
 */
public class BookingCancelledState implements IBookingState {
    @Override
    public void selectRoom(HotelBookingContext context, String roomNumber) {
        System.out.println("ℹ️ Текущее бронирование отменено. Создайте новое бронирование.");
    }

    @Override
    public void confirmBooking(HotelBookingContext context) {
        System.out.println("❌ Невозможно подтвердить отмененное бронирование.");
    }

    @Override
    public void makePayment(HotelBookingContext context, double amount) {
        System.out.println("❌ Невозможно оплатить отмененное бронирование.");
    }

    @Override
    public void cancelBooking(HotelBookingContext context) {
        System.out.println("ℹ️ Бронирование уже отменено.");
    }

    @Override
    public void changeRoom(HotelBookingContext context, String newRoomNumber) {
        System.out.println("❌ Невозможно изменить номер отмененного бронирования.");
    }

    @Override
    public String getStateName() {
        return "Отменено (Cancelled)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Создать новое бронирование");
    }
}
