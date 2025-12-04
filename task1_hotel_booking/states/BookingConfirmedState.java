package task1_hotel_booking.states;

import task1_hotel_booking.context.BookingContext;

public class BookingConfirmedState implements BookingState {

    @Override
    public void selectRoom(BookingContext context, String roomNumber) {
        System.out.println("Ошибка: Бронирование уже подтверждено. Отмените текущее бронирование для выбора другого номера.");
    }

    @Override
    public void changeRoom(BookingContext context, String newRoomNumber) {
        System.out.println("Ошибка: Нельзя изменить номер после подтверждения бронирования.");
    }

    @Override
    public void confirmBooking(BookingContext context) {
        System.out.println("Бронирование уже подтверждено.");
    }

    @Override
    public void pay(BookingContext context, double amount, double discount) {
        double roomPrice = context.getRoomPrice();
        double discountedPrice = roomPrice * (1 - discount / 100);
        
        if (amount >= discountedPrice) {
            context.setAmountPaid(amount);
            context.setDiscountApplied(discount);
            context.setState(new PaidState());
            System.out.println("Оплата принята. Сумма: " + amount + 
                    " (скидка " + discount + "%, цена со скидкой: " + discountedPrice + ").");
            System.out.println("Бронирование номера " + context.getSelectedRoom() + " завершено успешно.");
            context.saveToHistory();
        } else {
            System.out.println("Ошибка: Недостаточная сумма. Требуется: " + discountedPrice + ", получено: " + amount);
        }
    }

    @Override
    public void cancel(BookingContext context) {
        context.setState(new BookingCancelledState());
        System.out.println("Бронирование номера " + context.getSelectedRoom() + " отменено.");
        context.saveToHistory();
    }

    @Override
    public String getStateName() {
        return "BookingConfirmed";
    }
}
