package com.designpatterns.lab13.task1;

import java.util.Scanner;

/**
 * Демонстрация работы системы бронирования гостиницы
 */
public class Task1Demo {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║  Задание №1: Система бронирования номеров в гостинице        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();

        HotelBookingContext booking = new HotelBookingContext();

        while (true) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            booking.showCurrentState();
            System.out.println("\n🎯 Меню действий:");
            System.out.println("1. Выбрать номер");
            System.out.println("2. Подтвердить бронирование");
            System.out.println("3. Произвести оплату");
            System.out.println("4. Отменить бронирование");
            System.out.println("5. Изменить номер");
            System.out.println("6. Применить скидку");
            System.out.println("7. Показать историю");
            System.out.println("8. Начать новое бронирование");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("\nВыберите действие: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    System.out.print("Введите номер комнаты (например, 101, 205, 301): ");
                    String room = scanner.nextLine();
                    booking.selectRoom(room);
                    break;

                case "2":
                    booking.confirmBooking();
                    break;

                case "3":
                    System.out.print("Введите сумму оплаты: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        booking.makePayment(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Неверная сумма!");
                    }
                    break;

                case "4":
                    booking.cancelBooking();
                    break;

                case "5":
                    System.out.print("Введите новый номер комнаты: ");
                    String newRoom = scanner.nextLine();
                    booking.changeRoom(newRoom);
                    break;

                case "6":
                    applyDiscount(booking, scanner);
                    break;

                case "7":
                    booking.showHistory();
                    break;

                case "8":
                    booking = new HotelBookingContext();
                    System.out.println("✨ Создано новое бронирование!");
                    break;

                case "0":
                    return;

                default:
                    System.out.println("❌ Неверный выбор!");
                    break;
            }
        }
    }

    private static void applyDiscount(HotelBookingContext booking, Scanner scanner) {
        if (booking.getTotalAmount() == 0) {
            System.out.println("❌ Скидка применяется только после подтверждения бронирования.");
            return;
        }

        System.out.println("\n🎁 Выберите скидку:");
        System.out.println("1. 5% - Раннее бронирование");
        System.out.println("2. 10% - Постоянный клиент");
        System.out.println("3. 15% - VIP клиент");
        System.out.println("4. 20% - Специальное предложение");
        System.out.print("\nВыберите вариант: ");

        String choice = scanner.nextLine();
        double discountPercent = 0;

        switch (choice) {
            case "1":
                discountPercent = 0.05;
                System.out.println("✅ Применена скидка 5% - Раннее бронирование");
                break;
            case "2":
                discountPercent = 0.10;
                System.out.println("✅ Применена скидка 10% - Постоянный клиент");
                break;
            case "3":
                discountPercent = 0.15;
                System.out.println("✅ Применена скидка 15% - VIP клиент");
                break;
            case "4":
                discountPercent = 0.20;
                System.out.println("✅ Применена скидка 20% - Специальное предложение");
                break;
            default:
                System.out.println("❌ Неверный выбор!");
                return;
        }

        double discountAmount = booking.getTotalAmount() * discountPercent;
        booking.setDiscount(discountAmount);
        booking.addToHistory(String.format("Применена скидка: %.0f%%", discountPercent * 100));
        
        double originalAmount = booking.getTotalAmount();
        double finalAmount = originalAmount - discountAmount;
        System.out.printf("💰 Исходная сумма: %.2f₽%n", originalAmount);
        System.out.printf("💵 Сумма со скидкой: %.2f₽%n", finalAmount);
        System.out.printf("💸 Экономия: %.2f₽%n", discountAmount);
    }
}
