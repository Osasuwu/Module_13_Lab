package task1_hotel_booking;

import task1_hotel_booking.context.BookingContext;
import task1_hotel_booking.history.BookingHistory;

public class HotelBookingDemo {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация системы бронирования гостиницы ===\n");

        // Сценарий 1: Успешное бронирование с оплатой
        System.out.println(">>> Сценарий 1: Успешное бронирование <<<");
        BookingContext booking1 = new BookingContext("Иванов И.И.");
        booking1.printStatus();
        
        booking1.selectRoom("S101");
        booking1.printStatus();
        
        booking1.confirmBooking();
        booking1.printStatus();
        
        booking1.pay(5000);
        booking1.printStatus();

        // Сценарий 2: Изменение номера перед подтверждением
        System.out.println("\n>>> Сценарий 2: Изменение номера <<<");
        BookingContext booking2 = new BookingContext("Петров П.П.");
        
        booking2.selectRoom("D201");
        booking2.printStatus();
        
        booking2.changeRoom("L301");
        booking2.printStatus();
        
        booking2.confirmBooking();
        booking2.payWithDiscount(8000, 20);
        booking2.printStatus();

        // Сценарий 3: Отмена бронирования после подтверждения
        System.out.println("\n>>> Сценарий 3: Отмена бронирования <<<");
        BookingContext booking3 = new BookingContext("Сидоров С.С.");
        
        booking3.selectRoom("S102");
        booking3.confirmBooking();
        booking3.cancel();
        booking3.printStatus();

        // Сценарий 4: Попытка отменить оплаченное бронирование
        System.out.println("\n>>> Сценарий 4: Попытка отменить оплаченное бронирование <<<");
        BookingContext booking4 = new BookingContext("Козлов К.К.");
        
        booking4.selectRoom("D202");
        booking4.confirmBooking();
        booking4.pay(3500);
        booking4.cancel();

        // Сценарий 5: Ошибки при неправильной последовательности действий
        System.out.println("\n>>> Сценарий 5: Ошибки при неправильной последовательности <<<");
        BookingContext booking5 = new BookingContext("Новиков Н.Н.");
        
        booking5.confirmBooking();
        booking5.pay(1000);
        booking5.cancel();

        // Вывод истории бронирований
        BookingHistory.getInstance().printHistory();
    }
}
