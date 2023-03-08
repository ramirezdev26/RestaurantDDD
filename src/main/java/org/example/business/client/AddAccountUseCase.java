package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.client.values.AccountId;
import org.example.domain.client.Client;
import org.example.domain.client.commands.AddAccountCommand;
import org.example.domain.client.values.ClientId;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddAccountUseCase implements UseCaseForCommand<AddAccountCommand> {

    private final EventsRepository eventsRepository;

    public AddAccountUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddAccountCommand command) {
        List<DomainEvent> clientEvents = eventsRepository.findByAggregatedRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), clientEvents);
        client.addAnAccount(AccountId.of(command.getAccountId()), command.getEmail(), command.getPassword(), command.getUsername());
        return client.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
