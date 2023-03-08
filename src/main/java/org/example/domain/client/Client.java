package org.example.domain.client;

import org.example.domain.client.events.*;
import org.example.domain.client.values.AccountId;
import org.example.domain.client.values.ClientId;
import org.example.domain.client.values.DataId;
import org.example.domain.client.values.ReviewId;
import org.example.generic.AggregateRoot;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class Client extends AggregateRoot<ClientId> {

    protected Data data;
    protected Account account;
    protected Review review;

    public Client(ClientId id, DataId dataId, String name, String phone) {
        super(id);
        subscribe(new ClientChange(this));
        appendChange(new ClientCreated(dataId.value(), name, phone));
    }

    private Client(ClientId id) {
        super(id);
        subscribe(new ClientChange(this));
    }

    public static Client from(ClientId clientId, List<DomainEvent> events) {
        var client = new Client(clientId);
        events.forEach(client::applyEvent);
        return client;
    }

    public void addAReview(ReviewId id, String date, String description, Integer score){
        Objects.requireNonNull(id);
        Objects.requireNonNull(date);
        Objects.requireNonNull(description);
        Objects.requireNonNull(score);
        appendChange(new ReviewAdded(id.value(), date, description, score)).apply();
    }

    public void addAnAccount(AccountId id, String email, String password, String username){
        Objects.requireNonNull(id);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(username);
        appendChange(new AccountAdded(id.value(), email, password, username)).apply();
    }

}
