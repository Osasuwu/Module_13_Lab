package task3_online_courses.service;

import task3_online_courses.model.User;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> usersByEmail;
    private User currentUser;

    public AuthService() {
        this.usersByEmail = new HashMap<>();
        this.currentUser = null;
    }

    public boolean register(User user) {
        if (usersByEmail.containsKey(user.getEmail())) {
            System.out.println("Ошибка: Пользователь с таким email уже существует.");
            return false;
        }
        usersByEmail.put(user.getEmail(), user);
        System.out.println("Пользователь " + user.getName() + " зарегистрирован. Ожидается подтверждение email.");
        return true;
    }

    public boolean confirmEmail(String email, String confirmationCode) {
        User user = usersByEmail.get(email);
        if (user == null) {
            System.out.println("Ошибка: Пользователь не найден.");
            return false;
        }
        user.setEmailVerified(true);
        System.out.println("Email " + email + " подтвержден.");
        return true;
    }

    public boolean login(String email, String password) {
        User user = usersByEmail.get(email);
        if (user == null) {
            System.out.println("Ошибка: Пользователь не найден.");
            return false;
        }
        if (!user.isEmailVerified()) {
            System.out.println("Ошибка: Email не подтвержден.");
            return false;
        }
        if (!user.authenticate(password)) {
            System.out.println("Ошибка: Неверный пароль.");
            return false;
        }
        currentUser = user;
        System.out.println("Пользователь " + user.getName() + " вошел в систему.");
        return true;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("Пользователь " + currentUser.getName() + " вышел из системы.");
            currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getUserByEmail(String email) {
        return usersByEmail.get(email);
    }

    public Map<String, User> getAllUsers() {
        return new HashMap<>(usersByEmail);
    }
}
