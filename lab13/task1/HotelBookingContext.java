package com.designpatterns.lab13.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Контекст бронирования гостиницы
 */
public class HotelBookingContext {
    private IBookingState currentState;
    private String roomNumber;
    private double totalAmount;
    private double discount;
    private List<String> bookingHistory;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HotelBookingContext() {
        this.currentState = new IdleState();
        this.bookingHistory = new ArrayList<>();
        addToHistory("Система инициализирована");
    }

    public void setState(IBookingState state) {
        this.currentState = state;
        addToHistory("Переход в состояние: " + state.getStateName());
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void selectRoom(String roomNumber) {
        currentState.selectRoom(this, roomNumber);
    }

    public void confirmBooking() {
        currentState.confirmBooking(this);
    }

    public void makePayment(double amount) {
        currentState.makePayment(this, amount);
    }

    public void cancelBooking() {
        currentState.cancelBooking(this);
    }

    public void changeRoom(String newRoomNumber) {
        currentState.changeRoom(this, newRoomNumber);
    }

    public void addToHistory(String action) {
        String timestamp = LocalTime.now().format(TIME_FORMATTER);
        bookingHistory.add(String.format("[%s] %s", timestamp, action));
    }

    public void showHistory() {
        System.out.println("\n📜 История бронирования:");
        System.out.println("════════════════════════════════════════");
        for (String entry : bookingHistory) {
            System.out.println(entry);
        }
        System.out.println("════════════════════════════════════════");
    }

    public void showCurrentState() {
        System.out.println("\n📊 Текущее состояние: " + currentState.getStateName());
        if (roomNumber != null && !roomNumber.isEmpty()) {
            System.out.println("🏨 Номер: " + roomNumber);
        }
        if (totalAmount > 0) {
            System.out.printf("💰 Сумма: %.2f₽%n", totalAmount);
            if (discount > 0) {
                System.out.printf("🎁 Скидка: %.2f₽ (%.1f%%)%n", discount, (discount / totalAmount) * 100);
                System.out.printf("💵 К оплате: %.2f₽%n", totalAmount - discount);
            }
        }
        currentState.printAvailableActions();
    }

    public void resetBooking() {
        roomNumber = null;
        totalAmount = 0;
        discount = 0;
        setState(new IdleState());
        addToHistory("Бронирование сброшено");
    }
}
