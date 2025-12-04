package task3_online_courses.service;

import task3_online_courses.model.Category;
import task3_online_courses.model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private final Map<String, Course> coursesById;
    private final Map<String, Category> categoriesById;

    public CourseService() {
        this.coursesById = new HashMap<>();
        this.categoriesById = new HashMap<>();
    }

    public void addCourse(Course course) {
        coursesById.put(course.getCourseId(), course);
    }

    public Course getCourseById(String courseId) {
        return coursesById.get(courseId);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(coursesById.values());
    }

    public List<Course> getCoursesByCategory(Category category) {
        List<Course> result = new ArrayList<>();
        for (Course course : coursesById.values()) {
            if (course.getCategory() != null && 
                course.getCategory().getCategoryId().equals(category.getCategoryId())) {
                result.add(course);
            }
        }
        return result;
    }

    public List<Course> searchCourses(String keyword) {
        List<Course> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Course course : coursesById.values()) {
            if (course.getTitle().toLowerCase().contains(lowerKeyword) ||
                course.getDescription().toLowerCase().contains(lowerKeyword)) {
                result.add(course);
            }
        }
        return result;
    }

    public void addCategory(Category category) {
        categoriesById.put(category.getCategoryId(), category);
    }

    public Category getCategoryById(String categoryId) {
        return categoriesById.get(categoryId);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoriesById.values());
    }

    public void removeCategory(String categoryId) {
        categoriesById.remove(categoryId);
    }

    public void printAllCourses() {
        System.out.println("\n=== Список всех курсов ===");
        if (coursesById.isEmpty()) {
            System.out.println("Курсов нет.");
        } else {
            for (Course course : coursesById.values()) {
                System.out.println(course);
            }
        }
        System.out.println("==========================\n");
    }

    public void printAllCategories() {
        System.out.println("\n=== Список категорий ===");
        if (categoriesById.isEmpty()) {
            System.out.println("Категорий нет.");
        } else {
            for (Category category : categoriesById.values()) {
                System.out.println(category);
            }
        }
        System.out.println("========================\n");
    }
}
