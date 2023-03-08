package org.example.domain.client.values;

import org.example.generic.Identity;

public class ReviewId extends Identity {

    private ReviewId(String value) {
        super(value);
    }

    public ReviewId() {}

    public static ReviewId of(String value) {
        return new ReviewId(value);
    }

}
