package com.designpatterns.lab13.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

/**
 * Демонстрация работы системы управления онлайн-курсами
 */
public class Task3Demo {
    private static List<User> users = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static User currentUser = null;

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║  Задание №3: Система управления онлайн-курсами               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();

        initializeTestData();

        while (true) {
            if (currentUser == null) {
                showLoginMenu(scanner);
            } else {
                showUserMenu(scanner);
            }
        }
    }

    private static void initializeTestData() {
        // Создаем тестовых пользователей
        Administrator admin = new Administrator("Иван Админов", "admin@example.com", "admin123");
        Teacher teacher1 = new Teacher("Мария Учителева", "teacher1@example.com", "teacher123");
        Teacher teacher2 = new Teacher("Петр Преподавателев", "teacher2@example.com", "teacher123");
        Student student1 = new Student("Алексей Студентов", "student1@example.com", "student123");
        Student student2 = new Student("Ольга Ученикова", "student2@example.com", "student123");

        users.add(admin);
        users.add(teacher1);
        users.add(teacher2);
        users.add(student1);
        users.add(student2);

        // Создаем тестовые курсы
        Course course1 = new Course("C# для начинающих", "Основы программирования на C#", "Программирование", teacher1.getId());
        Course course2 = new Course("Паттерны проектирования", "Изучение основных паттернов проектирования", "Программирование", teacher1.getId());
        Course course3 = new Course("ASP.NET Core MVC", "Разработка веб-приложений на ASP.NET Core", "Web-разработка", teacher2.getId());

        course1.setEnrolledStudents(45);
        course2.setEnrolledStudents(32);
        course3.setEnrolledStudents(28);

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        teacher1.getCreatedCourses().add(course1.getId());
        teacher1.getCreatedCourses().add(course2.getId());
        teacher2.getCreatedCourses().add(course3.getId());
    }

    private static void showLoginMenu(Scanner scanner) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔐 Вход в систему");
        System.out.println("\n📋 Тестовые аккаунты:");
        System.out.println("1. Администратор - admin@example.com / admin123");
        System.out.println("2. Преподаватель 1 - teacher1@example.com / teacher123");
        System.out.println("3. Преподаватель 2 - teacher2@example.com / teacher123");
        System.out.println("4. Студент 1 - student1@example.com / student123");
        System.out.println("5. Студент 2 - student2@example.com / student123");
        System.out.println("\n6. Регистрация нового пользователя");
        System.out.println("0. Вернуться в главное меню");
        System.out.print("\nВыберите действие: ");

        String choice = scanner.nextLine();
        System.out.println();

        switch (choice) {
            case "1":
                login("admin@example.com", "admin123");
                break;
            case "2":
                login("teacher1@example.com", "teacher123");
                break;
            case "3":
                login("teacher2@example.com", "teacher123");
                break;
            case "4":
                login("student1@example.com", "student123");
                break;
            case "5":
                login("student2@example.com", "student123");
                break;
            case "6":
                registerNewUser(scanner);
                break;
            case "0":
                currentUser = null;
                return;
            default:
                System.out.println("❌ Неверный выбор!");
                break;
        }
    }

    private static void login(String email, String password) {
        Optional<User> user = users.stream()
            .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
            .findFirst();
        
        if (user.isPresent()) {
            currentUser = user.get();
            System.out.println("✅ Добро пожаловать, " + currentUser.getName() + "!");
            System.out.println("👤 Роль: " + currentUser.getRole());
        } else {
            System.out.println("❌ Неверный email или пароль!");
        }
    }

    private static void registerNewUser(Scanner scanner) {
        System.out.println("📝 Регистрация нового пользователя");
        System.out.println("\nВыберите роль:");
        System.out.println("1. Студент");
        System.out.println("2. Преподаватель");
        System.out.print("\nВаш выбор: ");

        String roleChoice = scanner.nextLine();

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User newUser = null;
        switch (roleChoice) {
            case "1":
                newUser = new Student(name, email, password);
                break;
            case "2":
                newUser = new Teacher(name, email, password);
                break;
            default:
                System.out.println("❌ Неверный выбор роли!");
                return;
        }

        users.add(newUser);
        System.out.println("\n✅ Пользователь успешно зарегистрирован!");
        System.out.println("🆔 ID: " + newUser.getId());
        System.out.println("📧 Email: " + newUser.getEmail());

        // Автоматический вход
        currentUser = newUser;
    }

    private static void showUserMenu(Scanner scanner) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("👤 Пользователь: " + currentUser.getName() + " (" + currentUser.getRole() + ")");

        if (currentUser instanceof Administrator) {
            showAdministratorMenu((Administrator) currentUser, scanner);
        } else if (currentUser instanceof Teacher) {
            showTeacherMenu((Teacher) currentUser, scanner);
        } else if (currentUser instanceof Student) {
            showStudentMenu((Student) currentUser, scanner);
        }
    }

    private static void showStudentMenu(Student student, Scanner scanner) {
        student.showMenu();
        System.out.println("0. Выход из аккаунта");
        System.out.print("\nВыберите действие: ");

        String choice = scanner.nextLine();
        System.out.println();

        switch (choice) {
            case "1":
                viewAvailableCourses();
                break;
            case "2":
                enrollToCourse(student, scanner);
                break;
            case "3":
                takeTest(student, scanner);
                break;
            case "4":
                student.viewProgress();
                break;
            case "5":
                leaveReview(student, scanner);
                break;
            case "0":
                currentUser = null;
                System.out.println("👋 До свидания!");
                break;
            default:
                System.out.println("❌ Неверный выбор!");
                break;
        }
    }

    private static void showTeacherMenu(Teacher teacher, Scanner scanner) {
        teacher.showMenu();
        System.out.println("0. Выход из аккаунта");
        System.out.print("\nВыберите действие: ");

        String choice = scanner.nextLine();
        System.out.println();

        switch (choice) {
            case "1":
                createCourse(teacher, scanner);
                break;
            case "2":
                editCourse(teacher, scanner);
                break;
            case "3":
                addMaterials(teacher, scanner);
                break;
            case "4":
                createTest(teacher, scanner);
                break;
            case "5":
                viewStatistics(teacher, scanner);
                break;
            case "6":
                moderateReviews(teacher, scanner);
                break;
            case "7":
                enrollToCourse(teacher, scanner);
                break;
            case "8":
                takeTest(teacher, scanner);
                break;
            case "0":
                currentUser = null;
                System.out.println("👋 До свидания!");
                break;
            default:
                System.out.println("❌ Неверный выбор!");
                break;
        }
    }

    private static void showAdministratorMenu(Administrator admin, Scanner scanner) {
        admin.showMenu();
        System.out.println("0. Выход из аккаунта");
        System.out.print("\nВыберите действие: ");

        String choice = scanner.nextLine();
        System.out.println();

        switch (choice) {
            case "1":
                manageUserAccounts(admin, scanner);
                break;
            case "2":
                createUserByAdmin(admin, scanner);
                break;
            case "3":
                deleteUserByAdmin(admin, scanner);
                break;
            case "4":
                blockUnblockUser(admin, scanner);
                break;
            case "5":
                manageCategories(admin, scanner);
                break;
            case "6":
                admin.viewSystemAnalytics();
                break;
            case "7":
                admin.viewCoursePopularity();
                break;
            case "8":
                createCourse(admin, scanner);
                break;
            case "9":
                enrollToCourse(admin, scanner);
                break;
            case "0":
                currentUser = null;
                System.out.println("👋 До свидания!");
                break;
            default:
                System.out.println("❌ Неверный выбор!");
                break;
        }
    }

    private static void viewAvailableCourses() {
        System.out.println("\n📚 Доступные курсы:");
        System.out.println("════════════════════════════════════════");
        
        if (courses.isEmpty()) {
            System.out.println("Нет доступных курсов.");
            return;
        }

        for (Course course : courses) {
            course.displayInfo();
            System.out.println();
        }
    }

    private static void enrollToCourse(Student student, Scanner scanner) {
        viewAvailableCourses();
        System.out.print("\nВведите ID курса для записи: ");
        String courseId = scanner.nextLine();

        Optional<Course> course = courses.stream()
            .filter(c -> c.getId().equals(courseId))
            .findFirst();
        
        if (course.isPresent()) {
            student.enrollToCourse(courseId);
            course.get().setEnrolledStudents(course.get().getEnrolledStudents() + 1);
        } else {
            System.out.println("❌ Курс не найден!");
        }
    }

    private static void takeTest(Student student, Scanner scanner) {
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("❌ Вы не записаны ни на один курс!");
            return;
        }

        System.out.println("\n📝 Ваши курсы:");
        List<String> enrolledCourses = student.getEnrolledCourses();
        for (int i = 0; i < enrolledCourses.size(); i++) {
            String courseId = enrolledCourses.get(i);
            Optional<Course> course = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst();
            System.out.println((i + 1) + ". " + (course.isPresent() ? course.get().getTitle() : courseId));
        }

        System.out.print("\nВыберите курс: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index > 0 && index <= enrolledCourses.size()) {
                String courseId = enrolledCourses.get(index - 1);
                System.out.println("\n📝 Прохождение теста...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                Random rnd = new Random();
                int score = 60 + rnd.nextInt(40);
                student.takeTest(courseId, score);
                
                // Обновляем прогресс
                int currentProgress = student.getCourseProgress().getOrDefault(courseId, 0);
                student.updateProgress(courseId, Math.min(100, currentProgress + 25));
            } else {
                System.out.println("❌ Неверный выбор!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверный ввод!");
        }
    }

    private static void leaveReview(Student student, Scanner scanner) {
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("❌ Вы не записаны ни на один курс!");
            return;
        }

        System.out.println("\n📝 Оставить отзыв на курс:");
        List<String> enrolledCourses = student.getEnrolledCourses();
        for (int i = 0; i < enrolledCourses.size(); i++) {
            String courseId = enrolledCourses.get(i);
            Optional<Course> course = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst();
            System.out.println((i + 1) + ". " + (course.isPresent() ? course.get().getTitle() : courseId));
        }

        System.out.print("\nВыберите курс: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index > 0 && index <= enrolledCourses.size()) {
                String courseId = enrolledCourses.get(index - 1);
                Optional<Course> course = courses.stream()
                    .filter(c -> c.getId().equals(courseId))
                    .findFirst();

                if (course.isPresent()) {
                    System.out.print("Оценка (1-5): ");
                    try {
                        int rating = Integer.parseInt(scanner.nextLine());
                        System.out.print("Комментарий: ");
                        String comment = scanner.nextLine();

                        Review review = new Review(student.getId(), student.getName(), courseId, rating, comment);
                        course.get().addReview(review);
                        System.out.println("✅ Отзыв отправлен на модерацию!");
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Неверная оценка!");
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверный ввод!");
        }
    }

    private static void createCourse(Teacher teacher, Scanner scanner) {
        System.out.println("\n📚 Создание нового курса");
        System.out.print("Название курса: ");
        String title = scanner.nextLine();

        System.out.print("Описание: ");
        String description = scanner.nextLine();

        System.out.print("Категория: ");
        String category = scanner.nextLine();

        Course course = new Course(title, description, category, teacher.getId());
        courses.add(course);
        teacher.createCourse(title, description, category);
        teacher.getCreatedCourses().add(course.getId());
    }

    private static void editCourse(Teacher teacher, Scanner scanner) {
        if (teacher.getCreatedCourses().isEmpty()) {
            System.out.println("❌ У вас нет созданных курсов!");
            return;
        }

        System.out.println("\n✏️ Ваши курсы:");
        List<String> createdCourses = teacher.getCreatedCourses();
        for (int i = 0; i < createdCourses.size(); i++) {
            String courseId = createdCourses.get(i);
            Optional<Course> course = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst();
            System.out.println((i + 1) + ". " + (course.isPresent() ? course.get().getTitle() : courseId));
        }

        System.out.print("\nВыберите курс для редактирования: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index > 0 && index <= createdCourses.size()) {
                String courseId = createdCourses.get(index - 1);
                System.out.print("Новое название: ");
                String newTitle = scanner.nextLine();
                System.out.print("Новое описание: ");
                String newDescription = scanner.nextLine();

                teacher.editCourse(courseId, newTitle, newDescription);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверный ввод!");
        }
    }

    private static void addMaterials(Teacher teacher, Scanner scanner) {
        if (teacher.getCreatedCourses().isEmpty()) {
            System.out.println("❌ У вас нет созданных курсов!");
            return;
        }

        System.out.println("\n📄 Добавление материалов");
        System.out.print("ID курса: ");
        String courseId = scanner.nextLine();

        System.out.print("Название материала: ");
        String materialName = scanner.nextLine();

        System.out.print("Тип (видео/презентация/документ): ");
        String materialType = scanner.nextLine();

        teacher.addMaterial(courseId, materialName, materialType);
    }

    private static void createTest(Teacher teacher, Scanner scanner) {
        if (teacher.getCreatedCourses().isEmpty()) {
            System.out.println("❌ У вас нет созданных курсов!");
            return;
        }

        System.out.println("\n📝 Создание теста");
        System.out.print("ID курса: ");
        String courseId = scanner.nextLine();

        System.out.print("Название теста: ");
        String testName = scanner.nextLine();

        System.out.print("Максимальный балл: ");
        try {
            int maxScore = Integer.parseInt(scanner.nextLine());
            teacher.createTest(courseId, testName, maxScore);
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверный балл!");
        }
    }

    private static void viewStatistics(Teacher teacher, Scanner scanner) {
        if (teacher.getCreatedCourses().isEmpty()) {
            System.out.println("❌ У вас нет созданных курсов!");
            return;
        }

        System.out.print("Введите ID курса: ");
        String courseId = scanner.nextLine();
        teacher.viewStudentStatistics(courseId);
    }

    private static void moderateReviews(Teacher teacher, Scanner scanner) {
        System.out.println("\n📝 Модерация отзывов");
        
        List<Course> coursesWithReviews = courses.stream()
            .filter(c -> teacher.getCreatedCourses().contains(c.getId()) && !c.getReviews().isEmpty())
            .toList();
        
        if (coursesWithReviews.isEmpty()) {
            System.out.println("❌ Нет отзывов для модерации!");
            return;
        }

        for (Course course : coursesWithReviews) {
            System.out.println("\n📚 Курс: " + course.getTitle());
            List<Review> unapprovedReviews = course.getReviews().stream()
                .filter(r -> !r.isApproved())
                .toList();
            
            for (Review review : unapprovedReviews) {
                review.display();
                System.out.print("\nОдобрить отзыв? (y/n): ");
                String response = scanner.nextLine();
                
                boolean approve = response != null && response.toLowerCase().equals("y");
                teacher.moderateReview(review.getId(), approve);
                review.setApproved(approve);
            }
        }
    }

    private static void manageUserAccounts(Administrator admin, Scanner scanner) {
        System.out.println("\n👥 Управление учетными записями");
        System.out.println("════════════════════════════════════════");
        
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ". " + user.getName() + " (" + user.getRole() + ") - " + user.getEmail());
        }

        System.out.print("\nВведите номер пользователя для управления: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index > 0 && index <= users.size()) {
                User user = users.get(index - 1);
                System.out.println("\nВыбран: " + user.getName());
                System.out.println("1. Просмотр информации");
                System.out.println("2. Изменить роль");
                System.out.print("\nВыберите действие: ");
                
                String action = scanner.nextLine();
                admin.manageUserAccount(user.getId(), action.equals("1") ? "Просмотр информации" : "Изменение роли");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Неверный ввод!");
        }
    }

    private static void createUserByAdmin(Administrator admin, Scanner scanner) {
        System.out.print("Выберите роль (1-Студент, 2-Преподаватель, 3-Администратор): ");
        String roleChoice = scanner.nextLine();
        
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();

        String role = switch (roleChoice) {
            case "1" -> "Студент";
            case "2" -> "Преподаватель";
            case "3" -> "Администратор";
            default -> "Студент";
        };

        admin.createUser(role, name, email);
    }

    private static void deleteUserByAdmin(Administrator admin, Scanner scanner) {
        System.out.print("Введите ID пользователя для удаления: ");
        String userId = scanner.nextLine();
        admin.deleteUser(userId);
    }

    private static void blockUnblockUser(Administrator admin, Scanner scanner) {
        System.out.print("Введите ID пользователя: ");
        String userId = scanner.nextLine();
        
        System.out.print("Заблокировать (b) или разблокировать (u)? ");
        String action = scanner.nextLine();

        if (action != null && action.toLowerCase().equals("b")) {
            admin.blockUser(userId);
        } else {
            admin.unblockUser(userId);
        }
    }

    private static void manageCategories(Administrator admin, Scanner scanner) {
        System.out.println("\n📂 Управление категориями");
        System.out.println("1. Создать категорию");
        System.out.println("2. Удалить категорию");
        System.out.print("\nВыберите действие: ");
        
        String choice = scanner.nextLine();
        
        if (choice.equals("1")) {
            System.out.print("Название категории: ");
            String name = scanner.nextLine();
            
            System.out.print("Описание: ");
            String description = scanner.nextLine();
            
            admin.createCategory(name, description);
        } else if (choice.equals("2")) {
            System.out.print("Название категории для удаления: ");
            String name = scanner.nextLine();
            admin.manageCourseCategory(name, "Удалена");
        }
    }
}
