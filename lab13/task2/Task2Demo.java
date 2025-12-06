package com.designpatterns.lab13.task2;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Демонстрация работы системы управления заявками на бронирование билетов
 */
public class Task2Demo {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║  Задание №2: Система управления заявками на билеты           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();

        BookingRequestContext request = null;

        while (true) {
            if (request != null) {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                request.showCurrentState();
            }

            System.out.println("\n🎯 Меню действий:");
            System.out.println("1. Создать новую заявку");
            System.out.println("2. Отправить заявку клиенту");
            System.out.println("3. Произвести оплату");
            System.out.println("4. Подтвердить бронирование");
            System.out.println("5. Отменить заявку");
            System.out.println("6. Показать историю заявки");
            System.out.println("7. Проверить срок оплаты");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("\nВыберите действие: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    request = createNewRequest(scanner);
                    break;

                case "2":
                    if (request != null) {
                        request.sendToClient();
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "3":
                    if (request != null) {
                        request.makePayment();
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "4":
                    if (request != null) {
                        request.confirmBooking();
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "5":
                    if (request != null) {
                        request.cancelRequest();
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "6":
                    if (request != null) {
                        request.showHistory();
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "7":
                    if (request != null) {
                        checkPaymentDeadline(request);
                    } else {
                        System.out.println("❌ Сначала создайте заявку!");
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("❌ Неверный выбор!");
                    break;
            }
        }
    }

    private static BookingRequestContext createNewRequest(Scanner scanner) {
        System.out.print("Введите имя клиента: ");
        String clientName = scanner.nextLine();

        System.out.print("Введите стоимость билета: ");
        try {
            double price = Double.parseDouble(scanner.nextLine());
            BookingRequestContext request = new BookingRequestContext(clientName, price);
            System.out.println("\n✅ Создана новая заявка " + request.getRequestId());
            return request;
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверная стоимость!");
            return null;
        }
    }

    private static void checkPaymentDeadline(BookingRequestContext request) {
        if (request.getPaymentDeadline() == null) {
            System.out.println("ℹ️ Срок оплаты не установлен. Отправьте заявку клиенту.");
            return;
        }

        if (request.isPaymentExpired()) {
            System.out.println("⚠️ ВНИМАНИЕ: Срок оплаты истек!");
            System.out.println("   При попытке оплаты заявка будет автоматически отменена.");
        } else {
            long secondsLeft = ChronoUnit.SECONDS.between(LocalDateTime.now(), request.getPaymentDeadline());
            long minutes = secondsLeft / 60;
            long seconds = secondsLeft % 60;
            System.out.println("✅ До истечения срока оплаты осталось:");
            System.out.printf("   %d минут %d секунд%n", minutes, seconds);
        }
    }
}
