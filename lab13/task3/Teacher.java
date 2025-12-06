package com.designpatterns.lab13.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Класс преподавателя (может выполнять функции студента)
 */
public class Teacher extends Student {
    private List<String> createdCourses;

    public Teacher(String name, String email, String password) {
        super(name, email, password);
        this.createdCourses = new ArrayList<>();
    }

    public List<String> getCreatedCourses() {
        return createdCourses;
    }

    public String createCourse(String title, String description, String category) {
        String courseId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        createdCourses.add(courseId);
        System.out.println("✅ Курс '" + title + "' создан успешно! ID: " + courseId);
        return courseId;
    }

    public void editCourse(String courseId, String newTitle, String newDescription) {
        if (createdCourses.contains(courseId)) {
            System.out.println("✅ Курс " + courseId + " обновлен!");
        } else {
            System.out.println("❌ Вы не являетесь автором этого курса!");
        }
    }

    public void addMaterial(String courseId, String materialName, String materialType) {
        if (createdCourses.contains(courseId)) {
            System.out.println("✅ Материал '" + materialName + "' (" + materialType + ") добавлен к курсу " + courseId);
        } else {
            System.out.println("❌ Вы не являетесь автором этого курса!");
        }
    }

    public void createTest(String courseId, String testName, int maxScore) {
        if (createdCourses.contains(courseId)) {
            System.out.println("✅ Тест '" + testName + "' создан для курса " + courseId);
            System.out.println("   Максимальный балл: " + maxScore);
        } else {
            System.out.println("❌ Вы не являетесь автором этого курса!");
        }
    }

    public void viewStudentStatistics(String courseId) {
        if (createdCourses.contains(courseId)) {
            Random rnd = new Random();
            System.out.println("\n📊 Статистика курса " + courseId + ":");
            System.out.println("════════════════════════════════════════");
            System.out.println("Студентов записано: " + (10 + rnd.nextInt(90)));
            System.out.println("Средний прогресс: " + (40 + rnd.nextInt(50)) + "%");
            System.out.println("Средний балл: " + (60 + rnd.nextInt(35)));
        } else {
            System.out.println("❌ Вы не являетесь автором этого курса!");
        }
    }

    public void moderateReview(String reviewId, boolean approve) {
        if (approve) {
            System.out.println("✅ Отзыв " + reviewId + " одобрен");
        } else {
            System.out.println("❌ Отзыв " + reviewId + " отклонен");
        }
    }

    @Override
    public void showMenu() {
        System.out.println("\n👨‍🏫 Меню преподавателя:");
        System.out.println("1. Создать курс");
        System.out.println("2. Редактировать курс");
        System.out.println("3. Добавить материалы");
        System.out.println("4. Создать тест");
        System.out.println("5. Просмотр статистики студентов");
        System.out.println("6. Модерация отзывов");
        System.out.println("7. [Как студент] Записаться на курс");
        System.out.println("8. [Как студент] Пройти тест");
    }

    @Override
    public String getRole() {
        return "Преподаватель";
    }
}
