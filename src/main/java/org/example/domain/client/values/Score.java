package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Score implements ValueObject<Integer> {

    private Integer score;

    public Score(Integer score) {
        this.score = score;
    }
    @Override
    public Integer value() {
        return score;
    }
}
