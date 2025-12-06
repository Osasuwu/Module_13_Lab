package com.designpatterns.lab13.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс студента
 */
public class Student extends User {
    private List<String> enrolledCourses;
    private Map<String, Integer> testResults;
    private Map<String, Integer> courseProgress;

    public Student(String name, String email, String password) {
        super(name, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.testResults = new HashMap<>();
        this.courseProgress = new HashMap<>();
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<String, Integer> getTestResults() {
        return testResults;
    }

    public Map<String, Integer> getCourseProgress() {
        return courseProgress;
    }

    public void enrollToCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            courseProgress.put(courseId, 0);
            System.out.println("✅ Вы успешно записались на курс!");
        } else {
            System.out.println("ℹ️ Вы уже записаны на этот курс.");
        }
    }

    public void takeTest(String courseId, int score) {
        if (enrolledCourses.contains(courseId)) {
            testResults.put(courseId, score);
            System.out.println("✅ Тест пройден! Ваш результат: " + score + " баллов");
        } else {
            System.out.println("❌ Вы не записаны на этот курс!");
        }
    }

    public void updateProgress(String courseId, int progress) {
        if (enrolledCourses.contains(courseId)) {
            courseProgress.put(courseId, Math.min(100, progress));
            System.out.println("📊 Прогресс обновлен: " + courseProgress.get(courseId) + "%");
        }
    }

    public void viewProgress() {
        System.out.println("\n📊 Ваш прогресс:");
        System.out.println("════════════════════════════════════════");
        for (String courseId : enrolledCourses) {
            int progress = courseProgress.getOrDefault(courseId, 0);
            int testScore = testResults.getOrDefault(courseId, 0);
            System.out.println("📚 Курс ID: " + courseId);
            System.out.println("   Прогресс: " + progress + "%");
            System.out.println("   Результат теста: " + testScore + " баллов");
            System.out.println();
        }
    }

    @Override
    public void showMenu() {
        System.out.println("\n👨‍🎓 Меню студента:");
        System.out.println("1. Просмотр доступных курсов");
        System.out.println("2. Записаться на курс");
        System.out.println("3. Пройти тест");
        System.out.println("4. Просмотр прогресса");
        System.out.println("5. Оставить отзыв");
    }

    @Override
    public String getRole() {
        return "Студент";
    }
}
