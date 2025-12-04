package task3_online_courses;

import task3_online_courses.model.*;
import task3_online_courses.service.AuthService;
import task3_online_courses.service.CourseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineCoursesDemo {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация системы управления онлайн-курсами ===\n");

        AuthService authService = new AuthService();
        CourseService courseService = new CourseService();

        // Создание пользователей
        System.out.println(">>> Регистрация пользователей <<<");
        Admin admin = new Admin("admin@courses.com", "admin123", "Администратор Системы");
        Teacher teacher1 = new Teacher("teacher1@courses.com", "teach123", "Иванов И.И.");
        Teacher teacher2 = new Teacher("teacher2@courses.com", "teach456", "Петров П.П.");
        Student student1 = new Student("student1@courses.com", "stud123", "Сидоров С.С.");
        Student student2 = new Student("student2@courses.com", "stud456", "Козлов К.К.");

        authService.register(admin);
        authService.register(teacher1);
        authService.register(teacher2);
        authService.register(student1);
        authService.register(student2);

        // Подтверждение email
        System.out.println("\n>>> Подтверждение email <<<");
        authService.confirmEmail("admin@courses.com", "123456");
        authService.confirmEmail("teacher1@courses.com", "123456");
        authService.confirmEmail("teacher2@courses.com", "123456");
        authService.confirmEmail("student1@courses.com", "123456");
        authService.confirmEmail("student2@courses.com", "123456");

        // Авторизация
        System.out.println("\n>>> Авторизация <<<");
        authService.login("admin@courses.com", "admin123");
        authService.logout();

        // Администратор создает категории
        System.out.println("\n>>> Создание категорий (Администратор) <<<");
        Category programming = admin.createCategory("Программирование", "Курсы по программированию");
        Category design = admin.createCategory("Дизайн", "Курсы по дизайну");
        Category marketing = admin.createCategory("Маркетинг", "Курсы по маркетингу");

        courseService.addCategory(programming);
        courseService.addCategory(design);
        courseService.addCategory(marketing);

        courseService.printAllCategories();

        // Преподаватели создают курсы
        System.out.println("\n>>> Создание курсов (Преподаватели) <<<");
        Course javaCourse = teacher1.createCourse(
                "Java для начинающих",
                "Полный курс Java от основ до продвинутых тем",
                programming
        );
        courseService.addCourse(javaCourse);

        Course pythonCourse = teacher1.createCourse(
                "Python с нуля",
                "Изучение Python для анализа данных",
                programming
        );
        courseService.addCourse(pythonCourse);

        Course uxCourse = teacher2.createCourse(
                "UX/UI Дизайн",
                "Основы проектирования пользовательского опыта",
                design
        );
        courseService.addCourse(uxCourse);

        // Добавление материалов
        System.out.println("\n>>> Добавление материалов в курсы <<<");
        teacher1.addMaterial(javaCourse, new CourseMaterial(
                "Введение в Java",
                "Содержание урока 1",
                CourseMaterial.MaterialType.VIDEO
        ));
        teacher1.addMaterial(javaCourse, new CourseMaterial(
                "Переменные и типы данных",
                "Содержание урока 2",
                CourseMaterial.MaterialType.TEXT
        ));

        // Создание тестов
        System.out.println("\n>>> Создание тестов <<<");
        Test javaTest1 = new Test("Тест по основам Java");
        javaTest1.addQuestion(new Test.Question(
                "Что такое JVM?",
                Arrays.asList("Компилятор", "Виртуальная машина", "IDE", "Библиотека"),
                1
        ));
        javaTest1.addQuestion(new Test.Question(
                "Какой модификатор доступа самый ограничительный?",
                Arrays.asList("public", "protected", "private", "default"),
                2
        ));
        teacher1.createTest(javaCourse, javaTest1);

        Test javaTest2 = new Test("Тест по ООП в Java");
        javaTest2.addQuestion(new Test.Question(
                "Что такое инкапсуляция?",
                Arrays.asList("Наследование", "Сокрытие данных", "Полиморфизм", "Абстракция"),
                1
        ));
        teacher1.createTest(javaCourse, javaTest2);

        // Студенты записываются на курсы
        System.out.println("\n>>> Запись студентов на курсы <<<");
        student1.enrollInCourse(javaCourse);
        student1.enrollInCourse(pythonCourse);
        student2.enrollInCourse(javaCourse);
        student2.enrollInCourse(uxCourse);

        // Студенты проходят тесты
        System.out.println("\n>>> Прохождение тестов <<<");
        student1.takeTest(javaCourse, javaTest1);
        student1.takeTest(javaCourse, javaTest2);
        student2.takeTest(javaCourse, javaTest1);

        // Просмотр прогресса
        System.out.println("\n>>> Просмотр прогресса студентов <<<");
        student1.viewProgress();
        student2.viewProgress();

        // Студенты оставляют отзывы
        System.out.println("\n>>> Оставление отзывов <<<");
        student1.leaveReview(javaCourse, "Отличный курс! Все понятно объяснено.", 5);
        student2.leaveReview(javaCourse, "Хороший курс, но хотелось бы больше практики.", 4);
        student2.leaveReview(uxCourse, "Интересные материалы.", 4);

        // Преподаватель модерирует отзывы
        System.out.println("\n>>> Модерация отзывов (Преподаватель) <<<");
        List<Review> javaReviews = javaCourse.getReviews();
        for (Review review : javaReviews) {
            teacher1.moderateReview(javaCourse, review, true);
        }

        // Преподаватель смотрит статистику
        System.out.println("\n>>> Статистика успеваемости (Преподаватель) <<<");
        teacher1.viewStudentStatistics(javaCourse);

        // Преподаватель пробует пройти тест для проверки
        System.out.println("\n>>> Преподаватель проходит свой курс для проверки <<<");
        teacher1.enrollInCourse(javaCourse);
        teacher1.takeTest(javaCourse, javaTest1);

        // Вывод информации о курсе
        javaCourse.printDetails();

        // Администратор смотрит аналитику
        System.out.println("\n>>> Аналитика системы (Администратор) <<<");
        List<User> allUsers = new ArrayList<>(authService.getAllUsers().values());
        admin.viewSystemAnalytics(courseService.getAllCourses(), allUsers);

        // Администратор управляет пользователями
        System.out.println("\n>>> Управление пользователями (Администратор) <<<");
        admin.manageUser(student1, Admin.UserAction.DEACTIVATE);
        admin.manageUser(student1, Admin.UserAction.ACTIVATE);

        // Вывод всех курсов
        courseService.printAllCourses();
    }
}
