package com.designpatterns.lab13.task2;

/**
 * Состояние: WaitingForPayment - Заявка ожидает оплаты
 */
public class WaitingForPaymentState implements IBookingRequestState {
    @Override
    public void sendToClient(BookingRequestContext context) {
        System.out.println("ℹ️ Заявка уже отправлена клиенту.");
    }

    @Override
    public void makePayment(BookingRequestContext context) {
        // Проверка срока оплаты
        if (context.isPaymentExpired()) {
            System.out.println("❌ Срок оплаты истек! Заявка автоматически отменена.");
            context.addToHistory("Срок оплаты истек - автоматическая отмена");
            context.setState(new CancelledState());
            return;
        }

        System.out.printf("✅ Оплата получена: %.2f₽%n", context.getTicketPrice());
        System.out.println("💳 Билет оплачен клиентом " + context.getClientName());
        context.addToHistory(String.format("Оплата получена: %.2f₽", context.getTicketPrice()));
        context.setState(new PaidState());
    }

    @Override
    public void confirmBooking(BookingRequestContext context) {
        System.out.println("❌ Невозможно подтвердить. Сначала необходимо получить оплату.");
    }

    @Override
    public void cancelRequest(BookingRequestContext context) {
        System.out.println("❌ Заявка " + context.getRequestId() + " отменена.");
        System.out.println("   Клиент не успел оплатить в установленный срок.");
        context.addToHistory("Заявка отменена - клиент не оплатил");
        context.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Ожидает оплаты (WaitingForPayment)";
    }

    @Override
    public void printAvailableActions() {
        System.out.println("\n✨ Доступные действия:");
        System.out.println("  → Произвести оплату (MakePayment)");
        System.out.println("  → Отменить заявку (CancelRequest)");
    }
}
