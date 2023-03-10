package org.example.domain.client.values;

import org.example.generic.Identity;

import java.util.Objects;

public class ReviewId extends Identity {

    private ReviewId(String value) {
        super(value);
    }

    public ReviewId() {}

    public static ReviewId of(String value) {
        if (!Objects.equals(value, "")) {
            return new ReviewId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
