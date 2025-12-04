package task3_online_courses.model;

import java.util.UUID;

public class CourseMaterial {
    private final String materialId;
    private String title;
    private String content;
    private MaterialType type;

    public CourseMaterial(String title, String content, MaterialType type) {
        this.materialId = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CourseMaterial{id='" + materialId + "', title='" + title + "', type=" + type + "}";
    }

    public enum MaterialType {
        VIDEO,
        TEXT,
        PDF,
        PRESENTATION,
        LINK
    }
}
