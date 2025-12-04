package task2_ticket_booking;

import task2_ticket_booking.context.BookingRequest;

public class TicketBookingDemo {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация системы бронирования билетов ===\n");

        // Сценарий 1: Успешное бронирование
        System.out.println(">>> Сценарий 1: Успешное бронирование <<<");
        BookingRequest request1 = new BookingRequest("Иванов И.И.", "Концерт группы XYZ", 15, 2500.0);
        request1.printStatus();
        
        request1.sendToClient();
        request1.printStatus();
        
        request1.processPayment(2500.0);
        request1.printStatus();
        
        request1.verifyBooking();
        request1.printStatus();

        // Сценарий 2: Отмена по таймауту
        System.out.println("\n>>> Сценарий 2: Отмена по таймауту <<<");
        BookingRequest request2 = new BookingRequest("Петров П.П.", "Театральная постановка", 8, 3000.0);
        request2.printStatus();
        
        request2.sendToClient();
        request2.printStatus();
        
        request2.paymentTimeout();
        request2.printStatus();

        // Сценарий 3: Ошибки при неправильной последовательности
        System.out.println("\n>>> Сценарий 3: Ошибки при неправильной последовательности <<<");
        BookingRequest request3 = new BookingRequest("Сидоров С.С.", "Спортивное мероприятие", 42, 1500.0);
        
        request3.processPayment(1500.0);
        request3.verifyBooking();
        
        request3.sendToClient();
        request3.verifyBooking();
        request3.processPayment(1500.0);
        request3.verifyBooking();
        request3.printStatus();

        // Сценарий 4: Попытка действий после отмены
        System.out.println("\n>>> Сценарий 4: Попытка действий после отмены <<<");
        BookingRequest request4 = new BookingRequest("Козлов К.К.", "Кинопоказ", 22, 800.0);
        request4.sendToClient();
        request4.paymentTimeout();
        
        request4.processPayment(800.0);
        request4.sendToClient();
        request4.printStatus();

        // Сценарий 5: Попытка действий после подтверждения
        System.out.println("\n>>> Сценарий 5: Попытка действий после подтверждения <<<");
        BookingRequest request5 = new BookingRequest("Новиков Н.Н.", "Выставка", 1, 500.0);
        request5.sendToClient();
        request5.processPayment(500.0);
        request5.verifyBooking();
        
        request5.sendToClient();
        request5.processPayment(500.0);
        request5.paymentTimeout();
        request5.printStatus();
    }
}
