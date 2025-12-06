package com.designpatterns.lab13.task3;

import java.util.Random;
import java.util.UUID;

/**
 * Класс администратора (может выполнять функции преподавателя и студента)
 */
public class Administrator extends Teacher {
    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    public void manageUserAccount(String userId, String action) {
        System.out.println("✅ Пользователь " + userId + ": " + action);
    }

    public void createUser(String role, String name, String email) {
        String userId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println("✅ Создан новый пользователь:");
        System.out.println("   Роль: " + role);
        System.out.println("   Имя: " + name);
        System.out.println("   Email: " + email);
        System.out.println("   ID: " + userId);
    }

    public void deleteUser(String userId) {
        System.out.println("❌ Пользователь " + userId + " удален из системы");
    }

    public void blockUser(String userId) {
        System.out.println("🚫 Пользователь " + userId + " заблокирован");
    }

    public void unblockUser(String userId) {
        System.out.println("✅ Пользователь " + userId + " разблокирован");
    }

    public void manageCourseCategory(String categoryName, String action) {
        System.out.println("✅ Категория '" + categoryName + "': " + action);
    }

    public void createCategory(String categoryName, String description) {
        System.out.println("✅ Создана новая категория курсов:");
        System.out.println("   Название: " + categoryName);
        System.out.println("   Описание: " + description);
    }

    public void viewSystemAnalytics() {
        Random rnd = new Random();
        System.out.println("\n📊 Аналитика системы:");
        System.out.println("════════════════════════════════════════");
        System.out.println("👥 Всего пользователей: " + (500 + rnd.nextInt(1500)));
        System.out.println("   • Студентов: " + (400 + rnd.nextInt(1400)));
        System.out.println("   • Преподавателей: " + (50 + rnd.nextInt(100)));
        System.out.println("   • Администраторов: " + (2 + rnd.nextInt(8)));
        System.out.println();
        System.out.println("📚 Всего курсов: " + (50 + rnd.nextInt(150)));
        System.out.println("   • Активных: " + (40 + rnd.nextInt(140)));
        System.out.println("   • В разработке: " + (5 + rnd.nextInt(15)));
        System.out.println();
        System.out.println("🔥 Популярные курсы:");
        System.out.println("   1. C# для начинающих (" + (100 + rnd.nextInt(400)) + " студентов)");
        System.out.println("   2. Паттерны проектирования (" + (80 + rnd.nextInt(220)) + " студентов)");
        System.out.println("   3. ASP.NET Core (" + (70 + rnd.nextInt(180)) + " студентов)");
        System.out.println();
        System.out.println("📈 Средняя успеваемость: " + (70 + rnd.nextInt(20)) + "%");
        System.out.printf("⭐ Средний рейтинг курсов: %.1f/5.0%n", (40 + rnd.nextInt(10)) / 10.0);
    }

    public void viewCoursePopularity() {
        Random rnd = new Random();
        System.out.println("\n🔥 Популярность курсов:");
        System.out.println("════════════════════════════════════════");
        for (int i = 1; i <= 5; i++) {
            String courseId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            int students = 50 + rnd.nextInt(250);
            double rating = (35 + rnd.nextInt(15)) / 10.0;
            System.out.println(i + ". Курс " + courseId);
            System.out.println("   Студентов: " + students);
            System.out.printf("   Рейтинг: %.1f/5.0%n", rating);
            System.out.println();
        }
    }

    @Override
    public void showMenu() {
        System.out.println("\n👨‍💼 Меню администратора:");
        System.out.println("1. Управление учетными записями");
        System.out.println("2. Создать пользователя");
        System.out.println("3. Удалить пользователя");
        System.out.println("4. Заблокировать/Разблокировать пользователя");
        System.out.println("5. Управление категориями курсов");
        System.out.println("6. Просмотр аналитики системы");
        System.out.println("7. Просмотр популярности курсов");
        System.out.println("8. [Как преподаватель] Создать курс");
        System.out.println("9. [Как студент] Записаться на курс");
    }

    @Override
    public String getRole() {
        return "Администратор";
    }
}
