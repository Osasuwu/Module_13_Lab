package task3_online_courses.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {
    private final List<Course> enrolledCourses;
    private final Map<String, Integer> courseProgress;
    private final Map<String, List<Integer>> testResults;

    public Student(String email, String password, String name) {
        super(email, password, name);
        this.enrolledCourses = new ArrayList<>();
        this.courseProgress = new HashMap<>();
        this.testResults = new HashMap<>();
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            courseProgress.put(course.getCourseId(), 0);
            testResults.put(course.getCourseId(), new ArrayList<>());
            course.addStudent(this);
            System.out.println("Студент " + getName() + " записан на курс: " + course.getTitle());
        } else {
            System.out.println("Студент уже записан на этот курс.");
        }
    }

    public void takeTest(Course course, Test test) {
        if (!enrolledCourses.contains(course)) {
            System.out.println("Ошибка: Студент не записан на курс.");
            return;
        }
        
        int score = test.evaluate();
        testResults.get(course.getCourseId()).add(score);
        updateProgress(course);
        System.out.println("Тест \"" + test.getTitle() + "\" пройден. Результат: " + score + "%");
    }

    private void updateProgress(Course course) {
        List<Integer> results = testResults.get(course.getCourseId());
        int totalTests = course.getTests().size();
        if (totalTests > 0) {
            int progress = (results.size() * 100) / totalTests;
            courseProgress.put(course.getCourseId(), Math.min(progress, 100));
        }
    }

    public void viewProgress() {
        System.out.println("\n=== Прогресс студента " + getName() + " ===");
        for (Course course : enrolledCourses) {
            int progress = courseProgress.getOrDefault(course.getCourseId(), 0);
            List<Integer> results = testResults.get(course.getCourseId());
            double avgScore = results.isEmpty() ? 0 : 
                    results.stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.println("Курс: " + course.getTitle() + 
                    ", Прогресс: " + progress + "%" +
                    ", Средний балл: " + String.format("%.1f", avgScore) + "%");
        }
        System.out.println("================================\n");
    }

    public void leaveReview(Course course, String reviewText, int rating) {
        if (!enrolledCourses.contains(course)) {
            System.out.println("Ошибка: Можно оставить отзыв только на курс, на который записан.");
            return;
        }
        Review review = new Review(this, reviewText, rating);
        course.addReview(review);
        System.out.println("Отзыв добавлен на курс: " + course.getTitle());
    }

    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    public int getCourseProgress(String courseId) {
        return courseProgress.getOrDefault(courseId, 0);
    }
}
