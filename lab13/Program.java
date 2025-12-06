package com.designpatterns.lab13;

import com.designpatterns.lab13.task1.Task1Demo;
import com.designpatterns.lab13.task2.Task2Demo;
import com.designpatterns.lab13.task3.Task3Demo;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║  Модуль 13: Диаграммы состояний и вариантов использования  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.println("\nВыберите задание:");
            System.out.println("1. Задание №1 - Система бронирования номеров в гостинице");
            System.out.println("2. Задание №2 - Система управления заявками на билеты");
            System.out.println("3. Задание №3 - Система управления онлайн-курсами");
            System.out.println("0. Выход");
            System.out.print("\nВаш выбор: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    Task1Demo.run();
                    break;
                case "2":
                    Task2Demo.run();
                    break;
                case "3":
                    Task3Demo.run();
                    break;
                case "0":
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
            
            System.out.println("\nНажмите Enter для продолжения...");
            scanner.nextLine();
            clearScreen();
        }
    }
    
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Если очистка не удалась, просто печатаем пустые строки
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
