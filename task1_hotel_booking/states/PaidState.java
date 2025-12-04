package task1_hotel_booking.states;

import task1_hotel_booking.context.BookingContext;

public class PaidState implements BookingState {

    @Override
    public void selectRoom(BookingContext context, String roomNumber) {
        System.out.println("Ошибка: Бронирование уже оплачено и завершено.");
    }

    @Override
    public void changeRoom(BookingContext context, String newRoomNumber) {
        System.out.println("Ошибка: Нельзя изменить номер после оплаты.");
    }

    @Override
    public void confirmBooking(BookingContext context) {
        System.out.println("Бронирование уже подтверждено и оплачено.");
    }

    @Override
    public void pay(BookingContext context, double amount, double discount) {
        System.out.println("Бронирование уже оплачено.");
    }

    @Override
    public void cancel(BookingContext context) {
        System.out.println("Ошибка: Нельзя отменить оплаченное бронирование.");
    }

    @Override
    public String getStateName() {
        return "Paid";
    }
}
