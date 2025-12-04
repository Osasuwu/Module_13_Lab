package task3_online_courses.model;

import java.util.UUID;

public class Category {
    private final String categoryId;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.categoryId = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{id='" + categoryId + "', name='" + name + "'}";
    }
}
