package task3_online_courses.model;

import java.util.List;

public class Admin extends Student {
    
    public Admin(String email, String password, String name) {
        super(email, password, name);
    }

    public void manageUser(User user, UserAction action) {
        switch (action) {
            case ACTIVATE:
                user.setEmailVerified(true);
                System.out.println("Пользователь " + user.getName() + " активирован.");
                break;
            case DEACTIVATE:
                user.setEmailVerified(false);
                System.out.println("Пользователь " + user.getName() + " деактивирован.");
                break;
            case DELETE:
                System.out.println("Пользователь " + user.getName() + " удален из системы.");
                break;
            case RESET_PASSWORD:
                user.setPassword("temporary123");
                System.out.println("Пароль пользователя " + user.getName() + " сброшен.");
                break;
        }
    }

    public Category createCategory(String name, String description) {
        Category category = new Category(name, description);
        System.out.println("Категория \"" + name + "\" создана.");
        return category;
    }

    public void editCategory(Category category, String newName, String newDescription) {
        category.setName(newName);
        category.setDescription(newDescription);
        System.out.println("Категория обновлена: " + newName);
    }

    public void deleteCategory(Category category) {
        System.out.println("Категория \"" + category.getName() + "\" удалена.");
    }

    public void viewSystemAnalytics(List<Course> allCourses, List<User> allUsers) {
        System.out.println("\n=== Аналитика системы ===");
        System.out.println("Всего пользователей: " + allUsers.size());
        
        long studentCount = allUsers.stream()
                .filter(u -> u instanceof Student && !(u instanceof Teacher) && !(u instanceof Admin))
                .count();
        long teacherCount = allUsers.stream()
                .filter(u -> u instanceof Teacher && !(u instanceof Admin))
                .count();
        long adminCount = allUsers.stream()
                .filter(u -> u instanceof Admin)
                .count();
        
        System.out.println("Студентов: " + studentCount);
        System.out.println("Преподавателей: " + teacherCount);
        System.out.println("Администраторов: " + adminCount);
        System.out.println("Всего курсов: " + allCourses.size());
        
        if (!allCourses.isEmpty()) {
            Course mostPopular = allCourses.stream()
                    .max((c1, c2) -> Integer.compare(
                            c1.getEnrolledStudents().size(), 
                            c2.getEnrolledStudents().size()))
                    .orElse(null);
            
            if (mostPopular != null) {
                System.out.println("Самый популярный курс: \"" + mostPopular.getTitle() + 
                        "\" (" + mostPopular.getEnrolledStudents().size() + " студентов)");
            }
        }
        System.out.println("=========================\n");
    }

    public enum UserAction {
        ACTIVATE,
        DEACTIVATE,
        DELETE,
        RESET_PASSWORD
    }
}
