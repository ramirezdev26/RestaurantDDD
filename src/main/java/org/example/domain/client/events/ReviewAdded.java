package org.example.domain.client.events;

import org.example.generic.DomainEvent;

public class ReviewAdded extends DomainEvent {

    private String reviewId;
    private String date;
    private String description;
    private Integer score;

    public ReviewAdded(String reviewId, String date, String description, Integer score) {
        super("restaurant.client.ReviewAdded");
        this.reviewId = reviewId;
        this.date = date;
        this.description = description;
        this.score = score;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Integer getScore() {
        return score;
    }
}
