package com.designpatterns.lab13.task2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Контекст заявки на бронирование билетов
 */
public class BookingRequestContext {
    private IBookingRequestState currentState;
    private String requestId;
    private String clientName;
    private double ticketPrice;
    private List<String> stateHistory;
    private LocalDateTime createdAt;
    private LocalDateTime paymentDeadline;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public BookingRequestContext(String clientName, double ticketPrice) {
        this.currentState = new CreatedState();
        this.requestId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.clientName = clientName;
        this.ticketPrice = ticketPrice;
        this.stateHistory = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        addToHistory("Заявка создана для клиента: " + clientName);
    }

    public void setState(IBookingRequestState state) {
        this.currentState = state;
        addToHistory("Переход в состояние: " + state.getStateName());
    }

    public String getRequestId() {
        return requestId;
    }

    public String getClientName() {
        return clientName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(LocalDateTime paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public void sendToClient() {
        currentState.sendToClient(this);
    }

    public void makePayment() {
        currentState.makePayment(this);
    }

    public void confirmBooking() {
        currentState.confirmBooking(this);
    }

    public void cancelRequest() {
        currentState.cancelRequest(this);
    }

    public void addToHistory(String action) {
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
        stateHistory.add(String.format("[%s] %s", timestamp, action));
    }

    public void showHistory() {
        System.out.println("\n📜 История заявки:");
        System.out.println("════════════════════════════════════════");
        for (String entry : stateHistory) {
            System.out.println(entry);
        }
        System.out.println("════════════════════════════════════════");
    }

    public void showCurrentState() {
        System.out.println("\n📊 Текущее состояние заявки: " + currentState.getStateName());
        System.out.println("🆔 ID заявки: " + requestId);
        System.out.println("👤 Клиент: " + clientName);
        System.out.printf("💰 Стоимость билета: %.2f₽%n", ticketPrice);
        System.out.println("🕒 Создана: " + createdAt.format(DATETIME_FORMATTER));
        
        if (paymentDeadline != null) {
            long secondsLeft = ChronoUnit.SECONDS.between(LocalDateTime.now(), paymentDeadline);
            if (secondsLeft > 0) {
                long minutes = secondsLeft / 60;
                long seconds = secondsLeft % 60;
                System.out.printf("⏰ До оплаты осталось: %d мин %d сек%n", minutes, seconds);
            } else {
                System.out.println("⏰ Срок оплаты истек!");
            }
        }
        
        currentState.printAvailableActions();
    }

    public boolean isPaymentExpired() {
        return paymentDeadline != null && LocalDateTime.now().isAfter(paymentDeadline);
    }
}
