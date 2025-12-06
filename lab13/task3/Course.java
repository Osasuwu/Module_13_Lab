package com.designpatterns.lab13.task3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс курса
 */
public class Course {
    private String id;
    private String title;
    private String description;
    private String category;
    private String authorId;
    private List<String> materials;
    private List<String> tests;
    private List<Review> reviews;
    private LocalDateTime createdAt;
    private int enrolledStudents;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Course(String title, String description, String category, String authorId) {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.title = title;
        this.description = description;
        this.category = category;
        this.authorId = authorId;
        this.materials = new ArrayList<>();
        this.tests = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.enrolledStudents = 0;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthorId() {
        return authorId;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getTests() {
        return tests;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void addMaterial(String materialName) {
        materials.add(materialName);
    }

    public void addTest(String testName) {
        tests.add(testName);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public void displayInfo() {
        System.out.println("\n📚 " + title);
        System.out.println("🆔 ID: " + id);
        System.out.println("📝 Описание: " + description);
        System.out.println("📂 Категория: " + category);
        System.out.println("👨‍🏫 Автор ID: " + authorId);
        System.out.println("👥 Записано студентов: " + enrolledStudents);
        System.out.println("📄 Материалов: " + materials.size());
        System.out.println("📝 Тестов: " + tests.size());
        System.out.printf("⭐ Рейтинг: %.1f/5.0 (%d отзывов)%n", getAverageRating(), reviews.size());
        System.out.println("📅 Создан: " + createdAt.format(DATE_FORMATTER));
    }
}
