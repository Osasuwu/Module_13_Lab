package task3_online_courses.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {
    private final String reviewId;
    private final Student author;
    private String text;
    private int rating;
    private final LocalDateTime createdAt;
    private boolean approved;

    public Review(Student author, String text, int rating) {
        this.reviewId = UUID.randomUUID().toString().substring(0, 8);
        this.author = author;
        this.text = text;
        this.rating = Math.max(1, Math.min(5, rating));
        this.createdAt = LocalDateTime.now();
        this.approved = false;
    }

    public String getReviewId() {
        return reviewId;
    }

    public Student getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = Math.max(1, Math.min(5, rating));
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Review{id='" + reviewId + 
                "', author='" + author.getName() + 
                "', rating=" + rating + 
                ", approved=" + approved + 
                ", text='" + text + "'}";
    }
}
