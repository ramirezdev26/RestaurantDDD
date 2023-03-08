package org.example.domain.client;

import org.example.domain.client.values.*;
import org.example.generic.Entity;

import java.util.Objects;

public class Review extends Entity<ReviewId> {

    private Date date;
    private Description description;
    private Score score;

    public Review(ReviewId entityId, Date date, Description description, Score score) {
        super(entityId);
        this.date = Objects.requireNonNull(date);
        this.description = Objects.requireNonNull(description);
        this.score = Objects.requireNonNull(score);
    }

    public Date date() { return this.date; }
    public Description description() { return this.description; }
    public Score score() { return this.score; }

}
