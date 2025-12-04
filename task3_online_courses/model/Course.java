package task3_online_courses.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    private final String courseId;
    private String title;
    private String description;
    private final Teacher author;
    private Category category;
    private final List<CourseMaterial> materials;
    private final List<Test> tests;
    private final List<Student> enrolledStudents;
    private final List<Review> reviews;

    public Course(String title, String description, Teacher author, Category category) {
        this.courseId = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
        this.materials = new ArrayList<>();
        this.tests = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
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

    public Teacher getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addMaterial(CourseMaterial material) {
        materials.add(material);
    }

    public List<CourseMaterial> getMaterials() {
        return new ArrayList<>(materials);
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public List<Test> getTests() {
        return new ArrayList<>(tests);
    }

    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public List<Review> getApprovedReviews() {
        List<Review> approved = new ArrayList<>();
        for (Review review : reviews) {
            if (review.isApproved()) {
                approved.add(review);
            }
        }
        return approved;
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        return reviews.stream()
                .filter(Review::isApproved)
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }

    @Override
    public String toString() {
        return "Course{id='" + courseId + "', title='" + title + 
                "', author='" + author.getName() + 
                "', category='" + (category != null ? category.getName() : "N/A") + 
                "', students=" + enrolledStudents.size() + "}";
    }

    public void printDetails() {
        System.out.println("\n=== Информация о курсе ===");
        System.out.println("ID: " + courseId);
        System.out.println("Название: " + title);
        System.out.println("Описание: " + description);
        System.out.println("Автор: " + author.getName());
        System.out.println("Категория: " + (category != null ? category.getName() : "Без категории"));
        System.out.println("Материалов: " + materials.size());
        System.out.println("Тестов: " + tests.size());
        System.out.println("Студентов: " + enrolledStudents.size());
        System.out.println("Рейтинг: " + String.format("%.1f", getAverageRating()) + "/5");
        System.out.println("===========================\n");
    }
}
