package org.example.domain.client.commands;

import org.example.generic.Command;

public class AddReviewCommand extends Command {

    private String reviewId;
    private String date;
    private String description;
    private Integer score;
    private String clientId;

    public AddReviewCommand(){}
    public AddReviewCommand(String reviewId, String date, String description, Integer score, String clientId) {
        this.reviewId = reviewId;
        this.date = date;
        this.description = description;
        this.score = score;
        this.clientId = clientId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
