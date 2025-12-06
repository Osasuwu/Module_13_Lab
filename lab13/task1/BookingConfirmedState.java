package com.designpatterns.lab13.task1;

/**
 * Состояние: BookingConfirmed - бронирование подтверждено, ожидается оплата
 */
public class BookingConfirmedState implements IBookingState {
    @Override
    public void selectRoom(HotelBookingContext context, String roomNumber) {
        System.out.println("❌ Невозможно выбрать новый номер. Бронирование уже подтверждено.");
    }

    @Override
    public void confirmBooking(HotelBookingContext context) {
        System.out.println("ℹ️ Бронирование уже подтверждено.");
    }

    @Override
    public void makePayment(HotelBookingContext context, double amount) {
        double finalAmount = context.getTotalAmount() - context.getDiscount();
        
        if (amount >= finalAmount) {
            System.out.printf("✅ Оплата успешно проведена! Сумма: %.2f₽%n", amount);
            context.addToHistory(String.format("Оплата проведена: %.2f₽", amount));
            context.setState(new PaidState());
        } else {
            System.out.printf("❌ Недостаточная сумма! Требуется: %.2f₽, предоставлено: %.2f₽%n", 
                            finalAmount, amount);
        }
    }

    @Override
    public void cancelBooking(HotelBookingContext context) {
        System.out.println("❌ Бронирование отменено. Возможен штраф за отмену.");
        context.addToHistory("Бронирование отменено после подтверждения");
        context.setState(new BookingCancelledState());
    }

    @Override
    public void changeRoom(HotelBookingContext context, String newRoomNumber) {
        System.out.println("🔄 Номер изменен с " + context.getRoomNumber() + " на " + newRoomNumber);
        context.addToHistory("Номер изменен после подтверждения: " + context.getRoomNumber() + 
                           " → " + newRoomNumber);
        context.setRoomNumber(newRoomNumber);
        System.out.println("⚠️ Внимание: возможна доплата за изменение номера.");
    }

    @Override
    public String getStateName() {
        return "Бронирование подтверждено (BookingConfirmed)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Произвести оплату (MakePayment)");
        System.out.println("  → Изменить номер (ChangeRoom)");
        System.out.println("  → Отменить бронирование (CancelBooking)");
    }
}
