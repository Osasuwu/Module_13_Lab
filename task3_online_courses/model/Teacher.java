package task3_online_courses.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Student {
    private final List<Course> createdCourses;

    public Teacher(String email, String password, String name) {
        super(email, password, name);
        this.createdCourses = new ArrayList<>();
    }

    public Course createCourse(String title, String description, Category category) {
        Course course = new Course(title, description, this, category);
        createdCourses.add(course);
        System.out.println("Курс \"" + title + "\" создан преподавателем " + getName());
        return course;
    }

    public void editCourse(Course course, String newTitle, String newDescription) {
        if (!createdCourses.contains(course)) {
            System.out.println("Ошибка: Вы не являетесь автором этого курса.");
            return;
        }
        course.setTitle(newTitle);
        course.setDescription(newDescription);
        System.out.println("Курс обновлен: " + newTitle);
    }

    public void addMaterial(Course course, CourseMaterial material) {
        if (!createdCourses.contains(course)) {
            System.out.println("Ошибка: Вы не являетесь автором этого курса.");
            return;
        }
        course.addMaterial(material);
        System.out.println("Материал \"" + material.getTitle() + "\" добавлен в курс " + course.getTitle());
    }

    public void createTest(Course course, Test test) {
        if (!createdCourses.contains(course)) {
            System.out.println("Ошибка: Вы не являетесь автором этого курса.");
            return;
        }
        course.addTest(test);
        System.out.println("Тест \"" + test.getTitle() + "\" добавлен в курс " + course.getTitle());
    }

    public void viewStudentStatistics(Course course) {
        if (!createdCourses.contains(course)) {
            System.out.println("Ошибка: Вы не являетесь автором этого курса.");
            return;
        }
        
        System.out.println("\n=== Статистика курса \"" + course.getTitle() + "\" ===");
        System.out.println("Количество студентов: " + course.getEnrolledStudents().size());
        
        for (Student student : course.getEnrolledStudents()) {
            int progress = student.getCourseProgress(course.getCourseId());
            System.out.println("Студент: " + student.getName() + ", Прогресс: " + progress + "%");
        }
        System.out.println("==========================================\n");
    }

    public void moderateReview(Course course, Review review, boolean approve) {
        if (!createdCourses.contains(course)) {
            System.out.println("Ошибка: Вы не являетесь автором этого курса.");
            return;
        }
        
        if (approve) {
            review.setApproved(true);
            System.out.println("Отзыв одобрен.");
        } else {
            course.removeReview(review);
            System.out.println("Отзыв удален.");
        }
    }

    public List<Course> getCreatedCourses() {
        return new ArrayList<>(createdCourses);
    }
}
