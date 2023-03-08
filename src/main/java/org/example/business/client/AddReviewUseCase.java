package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.client.Client;
import org.example.domain.client.commands.AddReviewCommand;
import org.example.domain.client.values.ClientId;
import org.example.domain.client.values.ReviewId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddReviewUseCase implements UseCaseForCommand<AddReviewCommand> {

    private final EventsRepository eventsRepository;

    public AddReviewUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddReviewCommand command) {
        List<DomainEvent> clientEvents = eventsRepository.findByAggregatedRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), clientEvents);
        client.addAReview(ReviewId.of(command.getReviewId()), command.getDate(), command.getDescription(), command.getScore());
        return client.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
