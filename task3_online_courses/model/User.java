package task3_online_courses.model;

import java.util.UUID;

public class User {
    private final String userId;
    private String email;
    private String password;
    private String name;
    private boolean emailVerified;

    public User(String email, String password, String name) {
        this.userId = UUID.randomUUID().toString().substring(0, 8);
        this.email = email;
        this.password = password;
        this.name = name;
        this.emailVerified = false;
    }

    public String getUserId() {
        return userId;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{id='" + userId + "', name='" + name + "', email='" + email + "'}";
    }
}
