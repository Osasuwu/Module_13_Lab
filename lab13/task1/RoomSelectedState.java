package com.designpatterns.lab13.task1;

import java.util.Random;

/**
 * Состояние: RoomSelected - номер выбран, но не подтвержден
 */
public class RoomSelectedState implements IBookingState {
    @Override
    public void selectRoom(HotelBookingContext context, String roomNumber) {
        System.out.println("ℹ️ Номер уже выбран. Используйте ChangeRoom для изменения.");
    }

    @Override
    public void confirmBooking(HotelBookingContext context) {
        System.out.println("✅ Бронирование номера " + context.getRoomNumber() + " подтверждено!");
        
        // Генерация стоимости на основе номера
        Random rnd = new Random();
        context.setTotalAmount(3000 + rnd.nextInt(7000));
        
        context.addToHistory(String.format("Бронирование подтверждено. Сумма: %.2f₽", context.getTotalAmount()));
        context.setState(new BookingConfirmedState());
    }

    @Override
    public void makePayment(HotelBookingContext context, double amount) {
        System.out.println("❌ Невозможно произвести оплату. Сначала подтвердите бронирование.");
    }

    @Override
    public void cancelBooking(HotelBookingContext context) {
        System.out.println("❌ Бронирование номера " + context.getRoomNumber() + " отменено.");
        context.addToHistory("Бронирование отменено на этапе выбора номера");
        context.setRoomNumber(null);
        context.setState(new BookingCancelledState());
    }

    @Override
    public void changeRoom(HotelBookingContext context, String newRoomNumber) {
        System.out.println("🔄 Номер изменен с " + context.getRoomNumber() + " на " + newRoomNumber);
        context.addToHistory("Номер изменен: " + context.getRoomNumber() + " → " + newRoomNumber);
        context.setRoomNumber(newRoomNumber);
    }

    @Override
    public String getStateName() {
        return "Номер выбран (RoomSelected)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Подтвердить бронирование (ConfirmBooking)");
        System.out.println("  → Изменить номер (ChangeRoom)");
        System.out.println("  → Отменить бронирование (CancelBooking)");
    }
}
