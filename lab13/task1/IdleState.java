package com.designpatterns.lab13.task1;

/**
 * Состояние: Idle - система ожидает действия пользователя
 */
public class IdleState implements IBookingState {
    @Override
    public void selectRoom(HotelBookingContext context, String roomNumber) {
        System.out.println("✅ Номер " + roomNumber + " выбран!");
        context.setRoomNumber(roomNumber);
        context.addToHistory("Выбран номер: " + roomNumber);
        context.setState(new RoomSelectedState());
    }

    @Override
    public void confirmBooking(HotelBookingContext context) {
        System.out.println("❌ Невозможно подтвердить бронирование. Сначала выберите номер.");
    }

    @Override
    public void makePayment(HotelBookingContext context, double amount) {
        System.out.println("❌ Невозможно произвести оплату. Сначала выберите номер.");
    }

    @Override
    public void cancelBooking(HotelBookingContext context) {
        System.out.println("ℹ️ Нет активного бронирования для отмены.");
    }

    @Override
    public void changeRoom(HotelBookingContext context, String newRoomNumber) {
        System.out.println("❌ Невозможно изменить номер. Сначала выберите номер.");
    }

    @Override
    public String getStateName() {
        return "Ожидание действий (Idle)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Выбрать номер (SelectRoom)");
    }
}
