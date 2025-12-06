package com.designpatterns.lab13.task2;

/**
 * Интерфейс состояния заявки на бронирование билетов (паттерн State)
 */
public interface IBookingRequestState {
    void sendToClient(BookingRequestContext context);
    void makePayment(BookingRequestContext context);
    void confirmBooking(BookingRequestContext context);
    void cancelRequest(BookingRequestContext context);
    String getStateName();
    void printAvailableActions();
}
