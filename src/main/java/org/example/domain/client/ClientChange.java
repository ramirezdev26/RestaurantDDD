package org.example.domain.client;

import org.example.domain.client.events.AccountAdded;
import org.example.domain.client.events.ClientCreated;
import org.example.domain.client.events.ReviewAdded;
import org.example.domain.client.values.*;
import org.example.generic.EventChange;

public class ClientChange extends EventChange {

    public ClientChange(Client client) {
        apply((ClientCreated event) -> {
            client.data = new Data (DataId.of(event.getDataId()), new Phone(event.getPhone()), new Name(event.getName()));
        });

        apply((ReviewAdded event) -> {
            client.review = new Review(ReviewId.of(event.getReviewId()), new Date(event.getDate()), new Description(event.getDescription()), new Score(event.getScore()));
        });

        apply((AccountAdded event) -> {
            client.account = new Account(AccountId.of(event.getAccountId()), new Email(event.getEmail()), new Password(event.getPassword()), new Username(event.getUsername()));
        });

    }
}
