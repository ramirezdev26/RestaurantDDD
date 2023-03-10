package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Score implements ValueObject<Integer> {

    private Integer score;

    public Score(Integer score) {
        if (score >= 0 && score <= 5){
            this.score = score;
        } else throw new IllegalArgumentException("The score must be between 0 and 5");
    }
    @Override
    public Integer value() {
        return score;
    }
}
