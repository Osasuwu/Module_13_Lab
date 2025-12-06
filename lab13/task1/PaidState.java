package com.designpatterns.lab13.task1;

/**
 * Состояние: Paid - бронирование оплачено
 */
public class PaidState implements IBookingState {
    @Override
    public void selectRoom(HotelBookingContext context, String roomNumber) {
        System.out.println("❌ Невозможно выбрать номер. Бронирование уже оплачено.");
    }

    @Override
    public void confirmBooking(HotelBookingContext context) {
        System.out.println("ℹ️ Бронирование уже подтверждено и оплачено.");
    }

    @Override
    public void makePayment(HotelBookingContext context, double amount) {
        System.out.println("ℹ️ Бронирование уже оплачено.");
    }

    @Override
    public void cancelBooking(HotelBookingContext context) {
        System.out.println("⚠️ Отмена оплаченного бронирования. Возврат средств может занять время.");
        context.addToHistory("Отмена оплаченного бронирования");
        context.setState(new BookingCancelledState());
    }

    @Override
    public void changeRoom(HotelBookingContext context, String newRoomNumber) {
        System.out.println("❌ Невозможно изменить номер после оплаты. Обратитесь в службу поддержки.");
    }

    @Override
    public String getStateName() {
        return "Оплачено (Paid)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Отменить бронирование с возвратом средств (CancelBooking)");
        System.out.println("✅ Бронирование успешно завершено!");
    }
}
