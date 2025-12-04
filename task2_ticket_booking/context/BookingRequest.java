package task2_ticket_booking.context;

import task2_ticket_booking.states.CreatedState;
import task2_ticket_booking.states.RequestState;

import java.util.UUID;

public class BookingRequest {
    private RequestState currentState;
    private final String requestId;
    private final String clientName;
    private final String eventName;
    private final int seatNumber;
    private final double ticketPrice;
    private final int paymentTimeoutMinutes;
    private double amountPaid;

    public BookingRequest(String clientName, String eventName, int seatNumber, double ticketPrice) {
        this.requestId = UUID.randomUUID().toString().substring(0, 8);
        this.clientName = clientName;
        this.eventName = eventName;
        this.seatNumber = seatNumber;
        this.ticketPrice = ticketPrice;
        this.paymentTimeoutMinutes = 15;
        this.currentState = new CreatedState();
        this.amountPaid = 0;
        
        System.out.println("Заявка #" + requestId + " создана.");
        System.out.println("Клиент: " + clientName + ", Мероприятие: " + eventName + ", Место: " + seatNumber);
    }

    public void setState(RequestState state) {
        this.currentState = state;
        System.out.println("[Состояние заявки #" + requestId + " изменено на: " + state.getStateName() + "]");
    }

    public RequestState getState() {
        return currentState;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getEventName() {
        return eventName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getPaymentTimeoutMinutes() {
        return paymentTimeoutMinutes;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void sendToClient() {
        currentState.sendToClient(this);
    }

    public void processPayment(double amount) {
        currentState.processPayment(this, amount);
    }

    public void paymentTimeout() {
        currentState.paymentTimeout(this);
    }

    public void verifyBooking() {
        currentState.verifyBooking(this);
    }

    public void printStatus() {
        System.out.println("\n--- Статус заявки ---");
        System.out.println("ID: " + requestId);
        System.out.println("Клиент: " + clientName);
        System.out.println("Мероприятие: " + eventName);
        System.out.println("Место: " + seatNumber);
        System.out.println("Цена билета: " + ticketPrice);
        System.out.println("Оплачено: " + amountPaid);
        System.out.println("Состояние: " + currentState.getStateName());
        System.out.println("---------------------\n");
    }
}
