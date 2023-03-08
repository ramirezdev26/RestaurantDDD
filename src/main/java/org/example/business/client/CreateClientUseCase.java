package org.example.business.client;

import org.example.business.commons.EventsRepository;
import org.example.business.commons.UseCaseForCommand;
import org.example.domain.client.Client;
import org.example.domain.client.commands.CreateClientCommand;
import org.example.domain.client.values.*;
import org.example.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateClientUseCase implements UseCaseForCommand<CreateClientCommand> {

    private final EventsRepository eventsRepository;

    public CreateClientUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateClientCommand command) {
        Client client = new Client(ClientId.of(command.getClientId()), DataId.of(command.getDataId()), command.getPhone(), command.getName());
        return client.getUncommittedChanges().stream().map(event -> eventsRepository.saveEvent(event)).toList();
    }
}
