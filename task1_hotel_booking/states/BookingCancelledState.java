package task1_hotel_booking.states;

import task1_hotel_booking.context.BookingContext;

public class BookingCancelledState implements BookingState {

    @Override
    public void selectRoom(BookingContext context, String roomNumber) {
        System.out.println("Ошибка: Бронирование отменено. Создайте новое бронирование.");
    }

    @Override
    public void changeRoom(BookingContext context, String newRoomNumber) {
        System.out.println("Ошибка: Бронирование отменено. Создайте новое бронирование.");
    }

    @Override
    public void confirmBooking(BookingContext context) {
        System.out.println("Ошибка: Бронирование отменено. Создайте новое бронирование.");
    }

    @Override
    public void pay(BookingContext context, double amount, double discount) {
        System.out.println("Ошибка: Бронирование отменено. Создайте новое бронирование.");
    }

    @Override
    public void cancel(BookingContext context) {
        System.out.println("Бронирование уже отменено.");
    }

    @Override
    public String getStateName() {
        return "BookingCancelled";
    }
}
