package com.designpatterns.lab13.task2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Состояние: Created - Заявка только что создана
 */
public class CreatedState implements IBookingRequestState {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void sendToClient(BookingRequestContext context) {
        System.out.println("✅ Заявка " + context.getRequestId() + " отправлена клиенту " + context.getClientName());
        
        // Устанавливаем срок оплаты (например, 5 минут)
        context.setPaymentDeadline(LocalDateTime.now().plusMinutes(5));
        
        System.out.println("⏰ Срок оплаты установлен до: " + context.getPaymentDeadline().format(TIME_FORMATTER));
        context.addToHistory("Заявка отправлена клиенту. Срок оплаты: 5 минут");
        context.setState(new WaitingForPaymentState());
    }

    @Override
    public void makePayment(BookingRequestContext context) {
        System.out.println("❌ Невозможно оплатить. Сначала отправьте заявку клиенту.");
    }

    @Override
    public void confirmBooking(BookingRequestContext context) {
        System.out.println("❌ Невозможно подтвердить. Заявка должна быть оплачена.");
    }

    @Override
    public void cancelRequest(BookingRequestContext context) {
        System.out.println("❌ Заявка " + context.getRequestId() + " отменена на этапе создания.");
        context.addToHistory("Заявка отменена после создания");
        context.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Создана (Created)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Отправить клиенту (SendToClient)");
        System.out.println("  → Отменить заявку (CancelRequest)");
    }
}
