package com.designpatterns.lab13.task3;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Базовый класс пользователя системы
 */
public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registeredAt;

    protected User(String name, String email, String password) {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.email = email;
        this.password = password;
        this.registeredAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public abstract void showMenu();
    public abstract String getRole();
}
