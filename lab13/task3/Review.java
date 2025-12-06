package com.designpatterns.lab13.task3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Класс отзыва на курс
 */
public class Review {
    private String id;
    private String studentId;
    private String studentName;
    private String courseId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private boolean isApproved;

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Review(String studentId, String studentName, String courseId, int rating, String comment) {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.rating = Math.max(1, Math.min(5, rating)); // Ограничение от 1 до 5
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
        this.isApproved = false;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = Math.max(1, Math.min(5, rating));
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void display() {
        System.out.println("\n⭐ Рейтинг: " + rating + "/5");
        System.out.println("👤 Автор: " + studentName);
        System.out.println("💬 Отзыв: " + comment);
        System.out.println("📅 Дата: " + createdAt.format(DATETIME_FORMATTER));
        System.out.println("✅ Статус: " + (isApproved ? "Одобрен" : "На модерации"));
    }
}
