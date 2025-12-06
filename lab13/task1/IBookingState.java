package com.designpatterns.lab13.task1;

/**
 * Интерфейс состояния бронирования (паттерн State)
 */
public interface IBookingState {
    void selectRoom(HotelBookingContext context, String roomNumber);
    void confirmBooking(HotelBookingContext context);
    void makePayment(HotelBookingContext context, double amount);
    void cancelBooking(HotelBookingContext context);
    void changeRoom(HotelBookingContext context, String newRoomNumber);
    String getStateName();
    void printAvailableActions();
}
