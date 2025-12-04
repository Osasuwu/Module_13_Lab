package task1_hotel_booking.states;

import task1_hotel_booking.context.BookingContext;

public class RoomSelectedState implements BookingState {

    @Override
    public void selectRoom(BookingContext context, String roomNumber) {
        System.out.println("Ошибка: Номер уже выбран. Используйте changeRoom для смены номера.");
    }

    @Override
    public void changeRoom(BookingContext context, String newRoomNumber) {
        String oldRoom = context.getSelectedRoom();
        context.setSelectedRoom(newRoomNumber);
        System.out.println("Номер изменен с " + oldRoom + " на " + newRoomNumber + ".");
    }

    @Override
    public void confirmBooking(BookingContext context) {
        context.setState(new BookingConfirmedState());
        System.out.println("Бронирование номера " + context.getSelectedRoom() + " подтверждено. Ожидается оплата.");
    }

    @Override
    public void pay(BookingContext context, double amount, double discount) {
        System.out.println("Ошибка: Сначала необходимо подтвердить бронирование.");
    }

    @Override
    public void cancel(BookingContext context) {
        String room = context.getSelectedRoom();
        context.setSelectedRoom(null);
        context.setState(new IdleState());
        System.out.println("Выбор номера " + room + " отменен. Возврат в начальное состояние.");
    }

    @Override
    public String getStateName() {
        return "RoomSelected";
    }
}
